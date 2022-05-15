package xyz.slimjim.hungrytales.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.common.response.BaseResponse;
import xyz.slimjim.hungrytales.service.api.RecipeService;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/{id}")
    public BaseResponse<RecipeItem> getRecipe(@PathVariable Integer id) {
        BaseResponse<RecipeItem> itemResponse = new BaseResponse<>();
        RecipeItem item = recipeService.getRecipe(id);
        item.setId(id);
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
    public BaseResponse<Integer> createRecipe(@RequestBody CreateRecipeRequest request) {
        System.out.println(request);
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
