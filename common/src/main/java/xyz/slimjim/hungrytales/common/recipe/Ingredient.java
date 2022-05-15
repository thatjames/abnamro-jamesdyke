package xyz.slimjim.hungrytales.common.recipe;

import xyz.slimjim.hungrytales.common.dataobject.DataObject;

public class Ingredient extends DataObject {

    private int id;
    private String name;
    private int amount;
    private String unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "IngredientItem{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", unit='" + unit + '\'' +
                ", id=" + id +
                '}';
    }
}
