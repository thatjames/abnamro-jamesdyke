package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.service.converter.ItemRequestConverter;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private ItemStorageService<RecipeItem> itemStorageService;

    @Override
    public int createRecipe(CreateRecipeRequest createRequest) {
        ItemRequestConverter<RecipeItem> updateItemRequestConverter = new CreateRecipeConverter();
        RecipeItem item = updateItemRequestConverter.convertRequest(createRequest);
        return itemStorageService.store(item);
    }

    @Override
    public void updateRecipe(UpdateRecipeRequest updateRecipeRequest) {

    }

    @Override
    public void deleteRecipe(int recipeId) {

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
