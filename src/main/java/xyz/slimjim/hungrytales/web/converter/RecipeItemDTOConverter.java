package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.cooking.CookingStep;
import xyz.slimjim.hungrytales.common.item.IngredientItem;
import xyz.slimjim.hungrytales.common.item.RecipeItem;
import xyz.slimjim.hungrytales.web.dto.CookingStepDTO;
import xyz.slimjim.hungrytales.web.dto.IngredientDTO;
import xyz.slimjim.hungrytales.web.dto.RecipeDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class RecipeItemDTOConverter implements ItemDTOConverter<RecipeItem, RecipeDTO> {

    private DateTimeFormatter formatter;

    public RecipeItemDTOConverter() {
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public RecipeDTO fromItemToDTO(RecipeItem item) {
        RecipeDTO dto = new RecipeDTO();
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
                    CookingStepDTO instructionDto = new CookingStepDTO();
                    instructionDto.setInstruction(instructionItem.getInstruction());
                    instructionDto.setStepNumber(instructionItem.getStepNumber());
                    return instructionDto;
                })
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public RecipeItem fromDTOToItem(RecipeDTO dto) {
        RecipeItem item = new RecipeItem();
        item.setAuthor(dto.getAuthor());
        item.setCreatedDatetime(LocalDateTime.parse(dto.getCreatedDatetime(), formatter));
        item.setFeeds(dto.getFeeds());
        item.setTitle(dto.getTitle());
        item.setVegetarian(dto.isVegetarian());
        item.setIngredients(dto.getIngredients().stream().map(ingredientDTO -> {
                    IngredientItem ingredientItem = new IngredientItem();
                    ingredientItem.setName(ingredientDTO.getName());
                    ingredientItem.setAmount(ingredientDTO.getAmount());
                    ingredientItem.setUnit(ingredientDTO.getUnit());
                    return ingredientItem;
                })
                .collect(Collectors.toList()));
        item.setInstructions(dto.getInstructions().stream().map(instructionDTO -> {
                    CookingStep instructionItem = new CookingStep();
                    instructionItem.setInstruction(instructionDTO.getInstruction());
                    instructionItem.setStepNumber(instructionDTO.getStepNumber());
                    return instructionItem;
                })
                .collect(Collectors.toList()));
        return item;
    }
}
