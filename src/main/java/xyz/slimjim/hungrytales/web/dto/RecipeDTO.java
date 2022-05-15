package xyz.slimjim.hungrytales.web.dto;

import xyz.slimjim.hungrytales.common.cooking.CookingStep;

import java.util.List;

public class RecipeDTO extends BaseDTO {

    private int id;
    private String author;
    private String title;
    private String createdDatetime;
    private boolean isVegetarian;
    private int feeds;
    private List<IngredientDTO> ingredients;
    private List<CookingStepDTO> instructions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(String createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public int getFeeds() {
        return feeds;
    }

    public void setFeeds(int feeds) {
        this.feeds = feeds;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<CookingStepDTO> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<CookingStepDTO> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", createdDatetime='" + createdDatetime + '\'' +
                ", isVegetarian=" + isVegetarian +
                ", feeds=" + feeds +
                ", ingredients=" + ingredients +
                ", instructions=" + instructions +
                '}';
    }
}
