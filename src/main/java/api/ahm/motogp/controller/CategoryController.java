package api.ahm.motogp.controller;

import api.ahm.motogp.entities.Category;
import api.ahm.motogp.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category, UriComponentsBuilder ucb) {
        category.setId(0);
        Category newCategory = categoryRepository.save(category);
        URI location = ucb.path("/categories/{id}").buildAndExpand(newCategory.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> putCategory(@PathVariable int id, @RequestBody Category category) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        category.setId(id);
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
