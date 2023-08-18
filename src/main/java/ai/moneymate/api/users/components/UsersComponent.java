package ai.moneymate.api.users.components;

import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsersComponent {
    private final UserRepository userRepository;

    public UserEntity getAuthenticatedUserFromDB() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = user.getId();

        return this.userRepository.findById(userId).get();
    }
}
