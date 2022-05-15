package xyz.slimjim.hungrytales.service.recipe;

import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.common.request.BaseRequest;
import xyz.slimjim.hungrytales.common.request.CreateRecipeRequest;
import xyz.slimjim.hungrytales.service.converter.ItemRequestConverter;

public class CreateRecipeConverter extends ItemRequestConverter<RecipeItem> {

    @Override
    public <R extends BaseRequest> RecipeItem convertRequest(R request) {
        RecipeItem item = new RecipeItem();
        CreateRecipeRequest createRecipeRequest = (CreateRecipeRequest) request;
        item.setAuthor(createRecipeRequest.getAuthor());
        item.setTags(createRecipeRequest.getTags());
        item.setTitle(createRecipeRequest.getTitle());
        item.setCreatedDatetime(createRecipeRequest.getCreatedDatetime());
        item.setVegetarian(createRecipeRequest.isVegetarian());
        item.setInstructions(createRecipeRequest.getInstructions());
        item.setIngredients(createRecipeRequest.getIngredients());
        item.setFeeds(createRecipeRequest.getFeeds());
        return item;
    }
}
