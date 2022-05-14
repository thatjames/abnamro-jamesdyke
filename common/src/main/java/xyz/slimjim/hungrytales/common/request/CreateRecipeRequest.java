package xyz.slimjim.hungrytales.common.request;

import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class CreateRecipeRequest extends BaseRequest {

    private String author;
    private String title;
    private List<String> tags;
    private LocalDateTime createdDatetime;
    private boolean isVegetarian;
    private int feeds;
    private List<IngredientItem> ingredients;
    private List<CookingStep> instructions;

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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Integer unixTimestamp) {
        this.createdDatetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp.longValue()), ZoneId.systemDefault());
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

    public List<IngredientItem> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientItem> ingredients) {
        this.ingredients = ingredients;
    }

    public List<CookingStep> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<CookingStep> instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "CreateRecipeRequest{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", createdDatetime=" + createdDatetime +
                ", isVegetarian=" + isVegetarian +
                ", feeds=" + feeds +
                ", ingredients=" + ingredients +
                ", instructions=" + instructions +
                '}';
    }
}