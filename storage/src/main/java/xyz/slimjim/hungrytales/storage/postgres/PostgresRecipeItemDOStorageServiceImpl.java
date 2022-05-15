package xyz.slimjim.hungrytales.storage.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.common.exceptions.RecordNotFoundException;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.storage.preparedstatement.batch.IngredientsPreparedStatementSetter;
import xyz.slimjim.hungrytales.storage.preparedstatement.batch.InstructionsPreparedStatementSetter;
import xyz.slimjim.hungrytales.storage.service.ItemStorageService;

import java.util.List;

@Repository
public class PostgresRecipeItemDOStorageServiceImpl implements ItemStorageService<RecipeItem> {

    private static final String RECIPE_SELECT_ID = "select * from recipe where id = ?";
    private static final String RECIPE_INGREDIENTS_SELECT_FROM_RECIPE_ID = "select name, unit, amount from recipe_ingredients where recipe_id = ?";
    private static final String RECIPE_INSTRUCTIONS_SELECT_FROM_RECIPE_ID = "select step_number, instruction from recipe_instructions where recipe_id = ?";
    private static final String INSERT_RECIPE_ITEM_FOR_ID = "insert into recipe (title, author, created_datetime, is_vegetarian, feeds) values (?, ?, ?, ?, ?) returning id;";
    private static final String INSERT_RECIPE_INSTRUCTION_ID_FOR_RECIPE_ID = "insert into recipe_instructions (recipe_id, step_number, instruction) values (?, ?, ?)";
    private static final String INSERT_RECIPE_INGREDIENTS_ID_FOR_RECIPE_ID = "insert into recipe_ingredients (recipe_id, name, amount, unit) values (?, ?, ?, ?)";
    private static final String DELETE_RECIPE_AND_INSTRUCTIONS_DEPENDENCIES_BY_ID = "delete from recipe_ingredients where recipe_id = ?; delete from recipe_instructions where recipe_id = ?";
    private static final String UPDATE_RECIPE = "update recipe set title=?, author=?, created_datetime=?, is_vegetarian=?, feeds=? where id=?";
    private static final String DELETE_RECIPE = "delete from recipe where id = ?";

    private static final Logger log = LoggerFactory.getLogger(PostgresRecipeItemDOStorageServiceImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public int store(RecipeItem itemDO) {
        try {
            Integer recipeId = jdbcTemplate.queryForObject(INSERT_RECIPE_ITEM_FOR_ID, Integer.class, itemDO.getTitle(), itemDO.getAuthor(), itemDO.getCreatedDatetime(), itemDO.isVegetarian(), itemDO.getFeeds());
            jdbcTemplate.batchUpdate(INSERT_RECIPE_INSTRUCTION_ID_FOR_RECIPE_ID, new InstructionsPreparedStatementSetter(itemDO.getInstructions(), recipeId));
            jdbcTemplate.batchUpdate(INSERT_RECIPE_INGREDIENTS_ID_FOR_RECIPE_ID, new IngredientsPreparedStatementSetter(itemDO.getIngredients(), recipeId));
            return recipeId;
        } catch (DataAccessException dax) {
            throw new HungryTalesException("Postgres error occurred", dax);
        }
    }

    @Override
    public RecipeItem get(int id) {
        try {
            RecipeItem item = jdbcTemplate.queryForObject(RECIPE_SELECT_ID, BeanPropertyRowMapper.newInstance(RecipeItem.class), id);
            List<IngredientItem> recipeIngredients = jdbcTemplate.query(RECIPE_INGREDIENTS_SELECT_FROM_RECIPE_ID, new BeanPropertyRowMapper<>(IngredientItem.class), id);
            List<CookingStep> cookingSteps = jdbcTemplate.query(RECIPE_INSTRUCTIONS_SELECT_FROM_RECIPE_ID, new BeanPropertyRowMapper<>(CookingStep.class), id);
            item.setInstructions(cookingSteps);
            item.setIngredients(recipeIngredients);
            return item;
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(ex.getMessage(), ex);
        } catch (DataAccessException dax) {
            throw new HungryTalesException("Postgres error occurred", dax);
        }
    }

    @Override
    @Transactional
    public void update(RecipeItem itemDO) {
        try {
            jdbcTemplate.update(UPDATE_RECIPE, itemDO.getTitle(), itemDO.getAuthor(), itemDO.getCreatedDatetime(), itemDO.isVegetarian(), itemDO.getFeeds(), itemDO.getId());
            jdbcTemplate.update(DELETE_RECIPE_AND_INSTRUCTIONS_DEPENDENCIES_BY_ID, itemDO.getId(), itemDO.getId());
            jdbcTemplate.batchUpdate(INSERT_RECIPE_INSTRUCTION_ID_FOR_RECIPE_ID, new InstructionsPreparedStatementSetter(itemDO.getInstructions(), itemDO.getId()));
            jdbcTemplate.batchUpdate(INSERT_RECIPE_INGREDIENTS_ID_FOR_RECIPE_ID, new IngredientsPreparedStatementSetter(itemDO.getIngredients(), itemDO.getId()));
        } catch (DataAccessException dax) {
            throw new HungryTalesException("Postgres error occurred", dax);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try {
            jdbcTemplate.update(DELETE_RECIPE, id);
            jdbcTemplate.update(DELETE_RECIPE_AND_INSTRUCTIONS_DEPENDENCIES_BY_ID, id);
        } catch (DataAccessException dax) {
            throw new HungryTalesException("Postgres error occurred", dax);
        }
    }
}