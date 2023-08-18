package ai.moneymate.api.accounts.repository;

import ai.moneymate.api.accounts.entities.AccountsEntity;
import ai.moneymate.api.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AccountsRepository extends JpaRepository<AccountsEntity, Integer> {
    Optional<Set<AccountsEntity>> findByUser(UserEntity user);
}
