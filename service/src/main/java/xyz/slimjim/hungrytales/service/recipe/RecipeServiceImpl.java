package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.service.converter.ItemRequestConverter;

import java.util.List;

@Component
public class RecipeServiceImpl implements RecipeService {


    @Override
    public void createRecipe(CreateRecipeRequest createRequest) {
        ItemRequestConverter<RecipeItem> updateItemRequestConverter = new CreateRecipeConverter();
        RecipeItem item = updateItemRequestConverter.convertRequest(createRequest);
    }

    @Override
    public void updateRecipe(UpdateRecipeRequest updateRecipeRequest) {

    }

    @Override
    public void deleteRecipe(int recipeId) {

    }

    @Override
    public RecipeItem getRecipe(int recipeId) {
        RecipeItem item = new RecipeItem();
        item.setTitle("Test Title");
        item.setTags(List.of("test", "recipe"));
        item.setBody("Test Body");
        item.setBlurb("Test Blurb");
        item.setAuthor("Test Author");
        return item;
    }

    @Override
    public List<RecipeItem> getAllItems() {
        return null;
    }
}
