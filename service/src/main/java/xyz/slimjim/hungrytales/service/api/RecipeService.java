package xyz.slimjim.hungrytales.service.api;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.recipe.Recipe;

import java.util.List;

/**
 * @author james dyke
 * Service Controller definition for basic recipe API interaction.
 */
@Component
public interface RecipeService {

    int createRecipe(Recipe createRequest);

    void updateRecipe(Recipe updateRecipeRequest);

    void deleteRecipe(int recipeId);

    Recipe getRecipe(int recipeId);

    List<Recipe> getAllItems();
}
