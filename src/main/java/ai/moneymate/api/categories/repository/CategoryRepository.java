package ai.moneymate.api.categories.repository;

import ai.moneymate.api.categories.entities.CategoriesEntity;
import ai.moneymate.api.users.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<CategoriesEntity, Integer> {
    Optional<Set<CategoriesEntity>> findByUser(UserEntity user);
}
