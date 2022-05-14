package xyz.slimjim.hungrytales.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.slimjim.hungrytales.service.api.RecipeService;

@RestController
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/test")
    public String test() {
        return recipeService.test();
    }
}
