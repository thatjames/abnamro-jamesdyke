package xyz.slimjim.hungrytales.service.api;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.item.RecipeItem;

import java.util.List;

/**
 * @author james dyke
 * Service Controller definition for basic recipe API interaction.
 */
@Component
public interface RecipeService {

    int createRecipe(RecipeItem createRequest);

    void updateRecipe(RecipeItem updateRecipeRequest);

    void deleteRecipe(int recipeId);

    RecipeItem getRecipe(int recipeId);

    List<RecipeItem> getAllItems();
}
