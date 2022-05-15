package xyz.slimjim.hungrytales.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.common.response.BaseResponse;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.common.exceptions.RecordNotFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private static final Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public BaseResponse<RecipeItem> getRecipe(@PathVariable Integer id, HttpServletResponse response) {
        log.info(String.format("Fetch recipe %d", id));
        BaseResponse<RecipeItem> itemResponse = new BaseResponse<>();
        try {
            RecipeItem item = recipeService.getRecipe(id);
            item.setId(id);
            itemResponse.setResult(true);
            itemResponse.setErrorMessage("");
            itemResponse.setData(item);
        } catch (RecordNotFoundException rnf) {
            log.error(String.format("No record found for %d", id));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            itemResponse.setErrorMessage(rnf.getMessage());
        } catch (Exception ex) {
            log.error("Unable to fetch recipe", ex);
            itemResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return itemResponse;
    }

    @PutMapping("/update")
    public void UpdateRecipe(@RequestBody UpdateRecipeRequest request) {
        recipeService.updateRecipe(request);
    }

    @PostMapping("/create")
    public BaseResponse<Integer> createRecipe(@RequestBody CreateRecipeRequest request) {
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        baseResponse.setData(recipeService.createRecipe(request));
        return baseResponse;
    }

    @DeleteMapping("/{id}")
    public void deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
    }

    @GetMapping("/list")
    public List<RecipeItem> getAll() {
        return recipeService.getAllItems();
    }
}
