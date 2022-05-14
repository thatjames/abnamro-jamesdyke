package xyz.slimjim.hungrytales.service.recipe;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.service.api.RecipeService;

@Component
public class RecipeServiceImpl implements RecipeService {

    @Override
    public String test() {
        return "Hello World";
    }
}
