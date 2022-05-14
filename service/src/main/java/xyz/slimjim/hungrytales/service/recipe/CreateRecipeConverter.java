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
        item.setBlurb(createRecipeRequest.getBlurb());
        item.setBody(createRecipeRequest.getBody());
        item.setTags(createRecipeRequest.getTags());
        item.setTitle(createRecipeRequest.getTitle());
        return item;
    }
}
