package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.recipe.Recipe;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.storage.service.DataObjectDAO;

import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private DataObjectDAO<Recipe> itemStorageService;

    @Override
    public int createRecipe(Recipe createRequest) {
        return itemStorageService.store(createRequest);
    }

    @Override
    public void updateRecipe(Recipe updateRecipeRequest) {
        itemStorageService.update(updateRecipeRequest);
    }

    @Override
    public void deleteRecipe(int recipeId) {
        itemStorageService.delete(recipeId);
    }

    @Override
    public Recipe getRecipe(int recipeId) {
        return itemStorageService.get(recipeId);
    }

    @Override
    public List<Recipe> getAllItems() {
        return itemStorageService.listAll();
    }
}
