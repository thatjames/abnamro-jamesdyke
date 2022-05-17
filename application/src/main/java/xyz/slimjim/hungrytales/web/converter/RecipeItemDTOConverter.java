package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.recipe.Instruction;
import xyz.slimjim.hungrytales.common.recipe.Ingredient;
import xyz.slimjim.hungrytales.common.recipe.Recipe;
import xyz.slimjim.hungrytales.web.dto.InstructionDTO;
import xyz.slimjim.hungrytales.web.dto.IngredientDTO;
import xyz.slimjim.hungrytales.web.dto.RecipeDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class RecipeItemDTOConverter implements DataObjectDTOConverter<Recipe, RecipeDTO> {

    private DateTimeFormatter formatter;

    public RecipeItemDTOConverter() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public RecipeDTO fromItemToDTO(Recipe item) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(item.getId());
        dto.setAuthor(item.getAuthor());
        dto.setCreatedDatetime(item.getCreatedDatetime().format(formatter));
        dto.setFeeds(item.getFeeds());
        dto.setTitle(item.getTitle());
        dto.setVegetarian(item.isVegetarian());
        dto.setIngredients(item.getIngredients().stream().map(ingredientItem -> {
                    IngredientDTO ingredientDTO = new IngredientDTO();
                    ingredientDTO.setName(ingredientItem.getName());
                    ingredientDTO.setAmount(ingredientItem.getAmount());
                    ingredientDTO.setUnit(ingredientItem.getUnit());
                    return ingredientDTO;
                })
                .collect(Collectors.toList()));
        dto.setInstructions(item.getInstructions().stream().map(instructionItem -> {
                    InstructionDTO instructionDto = new InstructionDTO();
                    instructionDto.setInstruction(instructionItem.getInstruction());
                    instructionDto.setStepNumber(instructionItem.getStepNumber());
                    return instructionDto;
                })
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public Recipe fromDTOToItem(RecipeDTO dto) {
        Recipe item = new Recipe();
        item.setId(dto.getId());
        item.setAuthor(dto.getAuthor());
        item.setCreatedDatetime(LocalDateTime.parse(dto.getCreatedDatetime(), formatter));
        item.setFeeds(dto.getFeeds());
        item.setTitle(dto.getTitle());
        item.setVegetarian(dto.isVegetarian());
        item.setIngredients(dto.getIngredients().stream().map(ingredientDTO -> {
                    Ingredient ingredientItem = new Ingredient();
                    ingredientItem.setName(ingredientDTO.getName());
                    ingredientItem.setAmount(ingredientDTO.getAmount());
                    ingredientItem.setUnit(ingredientDTO.getUnit());
                    return ingredientItem;
                })
                .collect(Collectors.toList()));
        item.setInstructions(dto.getInstructions().stream().map(instructionDTO -> {
                    Instruction instructionItem = new Instruction();
                    instructionItem.setInstruction(instructionDTO.getInstruction());
                    instructionItem.setStepNumber(instructionDTO.getStepNumber());
                    return instructionItem;
                })
                .collect(Collectors.toList()));
        return item;
    }
}
