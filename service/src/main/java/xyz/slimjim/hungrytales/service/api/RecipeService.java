package xyz.slimjim.hungrytales.service.api;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;

import java.util.List;

/**
 * @author james dyke
 * Service Controller definition for basic recipe API interaction.
 */
@Component
public interface RecipeService {

    void createRecipe(CreateRecipeRequest createRequest);

    void updateRecipe(UpdateRecipeRequest updateRecipeRequest);

    void deleteRecipe(int recipeId);

    RecipeItem getRecipe(int recipeId);

    List<RecipeItem> getAllItems();
}
