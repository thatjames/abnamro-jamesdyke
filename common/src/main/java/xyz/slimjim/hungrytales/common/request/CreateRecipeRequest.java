package xyz.slimjim.hungrytales.common.request;

import xyz.slimjim.hungrytales.common.request.BaseRequest;

import java.util.List;

public class CreateRecipeRequest extends BaseRequest {

    private String author;
    private String blurb;
    private String title;
    private String body;
    private List<String> tags;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlurb() {
        return blurb;
    }

    public void setBlurb(String blurb) {
        this.blurb = blurb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
                "author='" + author + '\'' +
                ", blurb='" + blurb + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", tags=" + tags +
                '}';
    }
}