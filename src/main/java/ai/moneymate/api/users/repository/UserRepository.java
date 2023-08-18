package ai.moneymate.api.users.repository;

import ai.moneymate.api.users.entities.UserEntity;
import ai.moneymate.api.users.exceptions.UserNotFoundException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username) throws UserNotFoundException;

    boolean existsByUsername(String username);
}
