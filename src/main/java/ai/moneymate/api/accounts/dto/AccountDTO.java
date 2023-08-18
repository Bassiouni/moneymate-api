package ai.moneymate.api.accounts.dto;

import ai.moneymate.api.accounts.entities.AccountsEntity;
import lombok.Builder;

@Builder
public record AccountDTO(int id, String name, String account_number, boolean is_default, int available_balance) {
    public static AccountDTO from(AccountsEntity account) {
        return AccountDTO
                .builder()
                .id(account.getId())
                .name(account.getName())
                .account_number(account.getAccount_number())
                .is_default(account.is_default())
                .available_balance(account.getAvailable_balance())
                .build();
    }
}
