package ai.moneymate.api.categories.service;

import ai.moneymate.api.categories.controllers.CategoryRequest;
import ai.moneymate.api.categories.dto.CategoryDTO;
import ai.moneymate.api.categories.entities.CategoriesEntity;
import ai.moneymate.api.categories.repository.CategoryRepository;
import ai.moneymate.api.users.components.UsersComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CategoriesService {
    private final CategoryRepository categoryRepository;
    private final UsersComponent usersComponent;

    public Set<CategoryDTO> getCategoriesForAuthenticatedUser() {
        Optional<Set<CategoriesEntity>> categories =
                this.categoryRepository.findByUser(usersComponent.getAuthenticatedUserFromDB());

        if (categories.isEmpty())
            throw new RuntimeException();

        Set<CategoryDTO> categoryDTOSet = new HashSet<>();

        for (CategoriesEntity category : categories.get()) {
            categoryDTOSet.add(CategoryDTO.from(category));
        }

        return categoryDTOSet;
    }

    public CategoryDTO createCategoryForAuthenticatedUser(CategoryRequest categoryRequest) {
        CategoriesEntity category = categoryRequest.asEntity();

        category.setUser(this.usersComponent.getAuthenticatedUserFromDB());

        return CategoryDTO.from(this.categoryRepository.save(category));
    }

    public CategoryDTO getCategoryInfoByID(int id) {
        Optional<CategoriesEntity> category = this.categoryRepository.findById(id);

        if (category.isEmpty())
            throw new RuntimeException();

        return CategoryDTO.from(category.get());
    }
}
