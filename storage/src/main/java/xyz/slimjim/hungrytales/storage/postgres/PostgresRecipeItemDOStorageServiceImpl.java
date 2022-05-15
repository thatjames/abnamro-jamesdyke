package xyz.slimjim.hungrytales.storage.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostgresRecipeItemDOStorageServiceImpl implements ItemStorageService<RecipeItem> {

    private static final String RECIPE_SELECT_ID = "select * from recipe where id = ?";
    private static final String RECIPE_INGREDIENTS_SELECT_FROM_RECIPE_ID = "select name, unit, amount from recipe_ingredients where recipe_id = ?";
    private static final String RECIPE_INSTRUCTIONS_SELECT_FROM_RECIPE_ID = "select step_number, instruction from recipe_instructions where recipe_id = ?";
    private static final String INSERT_RECIPE_ITEM_FOR_ID = "insert into recipe (title, author, created_datetime, is_vegetarian, feeds) values (?, ?, ?, ?, ?) returning id;";
    private static final String INSERT_RECIPE_INSTRUCTION_ID_FOR_RECIPE_ID = "insert into recipe_instructions (recipe_id, step_number, instruction) values (?, ?, ?)";
    private static final String INSERT_RECIPE_INGREDIENTS_ID_FOR_RECIPE_ID = "insert into recipe_ingredients (recipe_id, name, amount, unit) values (?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int store(RecipeItem itemDO) {
        System.out.println(itemDO.toString());
        int recipeId = jdbcTemplate.queryForObject(INSERT_RECIPE_ITEM_FOR_ID, Integer.class, itemDO.getTitle(), itemDO.getAuthor(), itemDO.getCreatedDatetime(), itemDO.isVegetarian(), itemDO.getFeeds());
        jdbcTemplate.batchUpdate(INSERT_RECIPE_INSTRUCTION_ID_FOR_RECIPE_ID, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CookingStep step = itemDO.getInstructions().get(i);
                ps.setInt(1, recipeId);
                ps.setInt(2, step.getStepNumber());
                ps.setString(3, step.getInstruction());
            }

            @Override
            public int getBatchSize() {
                return itemDO.getInstructions().size();
            }
        });
        jdbcTemplate.batchUpdate(INSERT_RECIPE_INGREDIENTS_ID_FOR_RECIPE_ID, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                IngredientItem ingredientItem = itemDO.getIngredients().get(i);
                ps.setInt(1, recipeId);
                ps.setString(2, ingredientItem.getName());
                ps.setInt(3, ingredientItem.getAmount());
                ps.setString(4, ingredientItem.getUnit());
            }

            @Override
            public int getBatchSize() {
                return itemDO.getIngredients().size();
            }
        });
        return recipeId;
    }

    @Override
    public RecipeItem get(int id) {
        RecipeItem item = jdbcTemplate.queryForObject(RECIPE_SELECT_ID, BeanPropertyRowMapper.newInstance(RecipeItem.class), id);
        List<IngredientItem> recipeIngredients = jdbcTemplate.query(RECIPE_INGREDIENTS_SELECT_FROM_RECIPE_ID, new BeanPropertyRowMapper<>(IngredientItem.class), id);
        List<CookingStep> cookingSteps = jdbcTemplate.query(RECIPE_INSTRUCTIONS_SELECT_FROM_RECIPE_ID, new BeanPropertyRowMapper<>(CookingStep.class), id);
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