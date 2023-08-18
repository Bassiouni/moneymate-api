package ai.moneymate.api.users.dto;

import ai.moneymate.api.users.entities.UserEntity;
import lombok.Builder;

@Builder
public record UserDTO (
    int id,
    String username
) {
    public static UserDTO from(UserEntity user) {
        return UserDTO
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
}
