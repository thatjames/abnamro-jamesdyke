package xyz.slimjim.hungrytales.storage.postgres;

import xyz.slimjim.hungrytales.storage.item.RecipeItemDO;
import xyz.slimjim.hungrytales.storage.service.ItemDOStorageService;

public class PostgresRecipeItemDOStorageServiceImpl implements ItemDOStorageService<RecipeItemDO> {

    @Override
    public int store(RecipeItemDO itemDO) {
        return 0;
    }

    @Override
    public RecipeItemDO get(int id) {
        return null;
    }

    @Override
    public void update(RecipeItemDO itemDO) {

    }

    @Override
    public void delete(int id) {

    }
}