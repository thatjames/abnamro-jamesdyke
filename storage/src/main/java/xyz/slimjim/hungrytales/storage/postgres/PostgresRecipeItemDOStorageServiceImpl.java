package xyz.slimjim.hungrytales.storage.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

@Repository
public class PostgresRecipeItemDOStorageServiceImpl implements ItemStorageService<RecipeItem> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int store(RecipeItem itemDO) {
        return 0;
    }

    @Override
    public RecipeItem get(int id) {
        RecipeItem item = jdbcTemplate.queryForObject("select * from recipe where id = ?", BeanPropertyRowMapper.newInstance(RecipeItem.class), id);
        return item;
    }

    @Override
    public void update(RecipeItem itemDO) {

    }

    @Override
    public void delete(int id) {

    }
}