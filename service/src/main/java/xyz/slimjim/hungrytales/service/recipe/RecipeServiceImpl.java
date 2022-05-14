package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.service.api.RecipeService;

import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Override
    public void createRecipe(CreateRecipeRequest createRequest) {
        RecipeItem item = new RecipeItem();
    }

    @Override
    public void updateRecipe(UpdateRecipeRequest updateRecipeRequest) {

    }

    @Override
    public void deleteRecipe(int recipeId) {

    }

    @Override
    public RecipeItem getRecipe(int recipeId) {
        return null;
    }

    @Override
    public List<RecipeItem> getAllItems() {
        return null;
    }
}
