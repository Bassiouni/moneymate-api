package ai.moneymate.api.categories.controllers;

import ai.moneymate.api.categories.dto.CategoryDTO;
import ai.moneymate.api.categories.service.CategoriesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;

    @GetMapping("{id}")
    public ResponseEntity<CategoryDTO> getCategoryByID(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.categoriesService.getCategoryInfoByID(id));
    }

    @GetMapping
    public ResponseEntity<Set<CategoryDTO>> getAuthenticatedUserCategories() {
        return ResponseEntity.status(HttpStatus.FOUND).body(this.categoriesService.getCategoriesForAuthenticatedUser());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createAccount(@Valid @RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.categoriesService.createCategoryForAuthenticatedUser(categoryRequest));
    }
}
