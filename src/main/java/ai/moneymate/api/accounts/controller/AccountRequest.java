package ai.moneymate.api.accounts.controller;

import ai.moneymate.api.accounts.entities.AccountsEntity;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AccountRequest(
    @NotNull
    @NotEmpty
    String name,

    @NotNull
    @NotEmpty
    String account_number,

    @NotNull
    @AssertTrue
    boolean is_default,

    @NotNull
    @PositiveOrZero
    int available_balance
) {
    public AccountsEntity asEntity() {
        return AccountsEntity
                .builder()
                .name(this.name)
                .account_number(this.account_number)
                .is_default(this.is_default)
                .available_balance(this.available_balance)
                .build();
    }
}
