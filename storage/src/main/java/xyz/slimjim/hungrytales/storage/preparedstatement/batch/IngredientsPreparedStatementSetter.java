package xyz.slimjim.hungrytales.storage.preparedstatement.batch;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import xyz.slimjim.hungrytales.common.recipe.Ingredient;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class IngredientsPreparedStatementSetter implements BatchPreparedStatementSetter {

    private int recipeId;
    private List<Ingredient> items;

    public IngredientsPreparedStatementSetter(List<Ingredient> items, int recipeId) {
        this.recipeId = recipeId;
        this.items = items;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Ingredient ingredientItem = items.get(i);
        ps.setInt(1, recipeId);
        ps.setString(2, ingredientItem.getName());
        ps.setInt(3, ingredientItem.getAmount());
        ps.setString(4, ingredientItem.getUnit());
    }

    @Override
    public int getBatchSize() {
        return items.size();
    }
}
