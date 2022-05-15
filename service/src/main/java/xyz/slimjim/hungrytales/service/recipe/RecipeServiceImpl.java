package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private ItemStorageService<RecipeItem> itemStorageService;

    @Override
    public int createRecipe(RecipeItem createRequest) {
        return itemStorageService.store(createRequest);
    }

    @Override
    public void updateRecipe(RecipeItem updateRecipeRequest) {
        itemStorageService.update(updateRecipeRequest);
    }

    @Override
    public void deleteRecipe(int recipeId) {
        itemStorageService.delete(recipeId);
    }

    @Override
    public RecipeItem getRecipe(int recipeId) {
        return itemStorageService.get(recipeId);
    }

    @Override
    public List<RecipeItem> getAllItems() {
        return null;
    }
}
