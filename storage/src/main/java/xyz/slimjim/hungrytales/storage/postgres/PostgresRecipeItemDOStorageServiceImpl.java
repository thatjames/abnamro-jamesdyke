package xyz.slimjim.hungrytales.storage.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

import java.util.List;

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
        List<IngredientItem> recipeIngredients = jdbcTemplate.query("select name, unit, amount from recipe_ingredients where recipe_id = ?", new BeanPropertyRowMapper<>(IngredientItem.class), id);
        List<CookingStep> cookingSteps = jdbcTemplate.query("select step_number, instruction from recipe_instructions where recipe_id = ?", new BeanPropertyRowMapper<>(CookingStep.class), id);
        item.setInstructions(cookingSteps);
        item.setIngredients(recipeIngredients);
        return item;
    }

    @Override
    public void update(RecipeItem itemDO) {

    }

    @Override
    public void delete(int id) {

    }
}