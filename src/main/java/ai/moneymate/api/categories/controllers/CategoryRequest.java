package ai.moneymate.api.categories.controllers;

import ai.moneymate.api.categories.entities.CategoriesEntity;
import ai.moneymate.api.categories.entities.CategoryType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CategoryRequest(
        @NotNull
        @NotEmpty
        String name,

        @NotNull
        @PositiveOrZero
        double assigned_budget,

        @NotNull
//        @NotEmpty
        CategoryType type
) {
    public CategoriesEntity asEntity() {
        return CategoriesEntity
                .builder()
                .name(this.name)
                .assigned_budget(this.assigned_budget)
                .type(this.type)
                .build();
    }
}
