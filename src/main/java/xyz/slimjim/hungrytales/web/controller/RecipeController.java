package xyz.slimjim.hungrytales.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.common.exceptions.RecordNotFoundException;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.web.converter.RecipeItemDTOConverter;
import xyz.slimjim.hungrytales.web.dto.RecipeCreateResultDTO;
import xyz.slimjim.hungrytales.web.dto.RecipeDTO;
import xyz.slimjim.hungrytales.web.response.WebResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public WebResponse<RecipeDTO> getRecipe(@PathVariable Integer id, HttpServletResponse response) {
        WebResponse<RecipeDTO> dtoResponse = new WebResponse<>();
        if (id < 1) {
            dtoResponse.setErrorMessage("bad request id");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return dtoResponse;
        }

        log.info(String.format("Fetch recipe %d", id));
        try {
            RecipeItemDTOConverter converter = new RecipeItemDTOConverter();
            RecipeItem item = recipeService.getRecipe(id);
            item.setId(id);
            dtoResponse.setResult(true);
            dtoResponse.setData(converter.fromItemToDTO(item));
        } catch (RecordNotFoundException rnf) {
            log.error(String.format("No record found for %d", id));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            dtoResponse.setErrorMessage(rnf.getMessage());
        } catch (Exception ex) {
            log.error("Unable to fetch recipe", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return dtoResponse;
    }

    @PutMapping("/update")
    public WebResponse<Void> updateRecipe(@RequestBody RecipeDTO request, HttpServletResponse response) {
        WebResponse<Void> dtoResponse = new WebResponse<>();
        if (request == null) {
            dtoResponse.setErrorMessage("bad request id");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return dtoResponse;
        }
        log.info(String.format("updateRecipe %s", request));
        try {
            RecipeItemDTOConverter converter = new RecipeItemDTOConverter();
            recipeService.updateRecipe(converter.fromDTOToItem(request));
            dtoResponse.setResult(true);
        } catch (HungryTalesException hex) {
            log.error("Bad update request", hex);
            dtoResponse.setErrorMessage(hex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception ex) {
            log.error("Unable to update request", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return dtoResponse;
    }

    @PostMapping("/create")
    public WebResponse<RecipeCreateResultDTO> createRecipe(@RequestBody RecipeDTO request, HttpServletResponse response) {
        WebResponse<RecipeCreateResultDTO> dtoResponse = new WebResponse<>();
        if (request == null) {
            dtoResponse.setErrorMessage("bad request");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return dtoResponse;
        }
        log.info(String.format("createRecipe %s", request));
        try {
            RecipeItemDTOConverter converter = new RecipeItemDTOConverter();
            RecipeCreateResultDTO result = new RecipeCreateResultDTO();
            result.setRecipeId(recipeService.createRecipe(converter.fromDTOToItem(request)));
            dtoResponse.setData(result);
        } catch (Exception ex) {
            log.error("Unable to update recipe", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return dtoResponse;
    }

    @DeleteMapping("/{id}")
    public WebResponse<Void> deleteRecipe(@PathVariable Integer id, HttpServletResponse response) {
        WebResponse<Void> dtoResponse = new WebResponse<>();
        if (id < 1) {
            dtoResponse.setErrorMessage("bad request");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return dtoResponse;
        }
        try {
            recipeService.deleteRecipe(id);
        } catch (Exception ex) {
            log.error("Unable to delete recipe", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return dtoResponse;
    }

    @GetMapping("/list")
    public List<RecipeDTO> getAll() {
        RecipeItemDTOConverter converter = new RecipeItemDTOConverter();
        return recipeService.getAllItems().stream().map(converter::fromItemToDTO).collect(Collectors.toList());
    }
}
