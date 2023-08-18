package ai.moneymate.api.categories.dto;

import ai.moneymate.api.categories.entities.CategoriesEntity;
import ai.moneymate.api.categories.entities.CategoryType;
import lombok.Builder;

@Builder
public record CategoryDTO(
        String name,

        double assigned_budget,

        CategoryType type
) {
    public static CategoryDTO from(CategoriesEntity category) {
        return CategoryDTO
                .builder()
                .name(category.getName())
                .assigned_budget(category.getAssigned_budget())
                .type(category.getType())
                .build();
    }
}
