package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.common.request.UpdateRecipeRequest;
import xyz.slimjim.hungrytales.service.api.RecipeService;
import xyz.slimjim.hungrytales.service.converter.ItemRequestConverter;

import java.util.ArrayList;
import java.util.Collections;
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
        item.setAuthor("Test Author");
        item.setCreatedDatetime("10-03-1991 15:02");
        item.setFeeds(3);
        item.setVegetarian(false);

        IngredientItem ingredientItem = new IngredientItem();
        ingredientItem.setAmount(200);
        ingredientItem.setName("Milk");
        ingredientItem.setUnit("ml");
        item.setIngredients(List.of(ingredientItem));

        CookingStep step1 = new CookingStep();
        step1.setStepNumber(1);
        step1.setInstruction("Pour milk into pan");
        List<CookingStep> instructions = new ArrayList<>();

        CookingStep step2 = new CookingStep();
        step2.setStepNumber(2);
        step2.setInstruction("Bring pan to boil");

        CookingStep step3 = new CookingStep();
        step3.setStepNumber(3);
        step3.setInstruction("Simmer for 10 minutes");

        CookingStep step4 = new CookingStep();
        step4.setStepNumber(4);
        step4.setInstruction("Serve");

        instructions.add(step2);
        instructions.add(step1);
        instructions.add(step4);
        instructions.add(step3);
        Collections.sort(instructions);
        item.setInstructions(instructions);
        return item;
    }

    @Override
    public List<RecipeItem> getAllItems() {
        return null;
    }
}
