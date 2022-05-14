package xyz.slimjim.hungrytales.common.response;

import xyz.slimjim.hungrytales.common.item.RecipeItem;

public class RecipeResponse extends BaseResponse<RecipeItem>{

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setData(RecipeItem data) {
        this.data = data;
    }

    @Override
    public RecipeItem getData() {
        return this.data;
    }
}
