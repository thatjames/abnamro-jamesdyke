package xyz.slimjim.hungrytales.storage.preparedstatement.batch;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import xyz.slimjim.hungrytales.common.cooking.CookingStep;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InstructionsPreparedStatementSetter implements BatchPreparedStatementSetter {

    private List<CookingStep> items;
    private int recipeId;

    public InstructionsPreparedStatementSetter(List<CookingStep> items, int recipeId) {
        this.items = items;
        this.recipeId = recipeId;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        CookingStep step = items.get(i);
        ps.setInt(1, recipeId);
        ps.setInt(2, step.getStepNumber());
        ps.setString(3, step.getInstruction());
    }

    @Override
    public int getBatchSize() {
        return items.size();
    }
}
