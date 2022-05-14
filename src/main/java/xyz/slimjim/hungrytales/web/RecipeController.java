package xyz.slimjim.hungrytales.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.common.response.BaseResponse;
import xyz.slimjim.hungrytales.common.response.RecipeResponse;
import xyz.slimjim.hungrytales.service.api.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public BaseResponse<RecipeItem> getRecipe(@PathVariable Integer id) {
        RecipeItem item = recipeService.getRecipe(id);
        RecipeResponse itemResponse = new RecipeResponse();
        itemResponse.setId(id);
        itemResponse.setResult(true);
        itemResponse.setErrorMessage("");
        itemResponse.setData(item);
        return itemResponse;
    }

    @PutMapping("/update")
    public void UpdateRecipe(@RequestBody UpdateRecipeRequest request) {
        recipeService.updateRecipe(request);
    }

    @PostMapping("/create")
    public void createRecipe(@RequestBody CreateRecipeRequest request) {
        recipeService.createRecipe(request);
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
