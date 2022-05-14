package xyz.slimjim.hungrytales.common.request;

import xyz.slimjim.hungrytales.common.request.BaseRequest;

import java.util.List;

public class CreateRecipeRequest extends BaseRequest {

    private String name;
    private String blurb;
    private String description;
    private String author;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CreateRecipeRequest{" +
                "name='" + name + '\'' +
                ", blurb='" + blurb + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
