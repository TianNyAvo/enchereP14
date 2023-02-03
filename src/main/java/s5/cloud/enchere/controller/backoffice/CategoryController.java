package s5.cloud.enchere.controller.backoffice;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import s5.cloud.enchere.model.enchere.Category;
import s5.cloud.enchere.model.responses.Error;
import s5.cloud.enchere.model.responses.Success;
import s5.cloud.enchere.service.TokenService;
import s5.cloud.enchere.service.backofficce.CategoryService;

@RestController
public class CategoryController {
    private final CategoryService categoryService;
    private final TokenService tokenService;


    @Autowired
    public CategoryController(CategoryService categoryService, TokenService tokenService) {
        this.categoryService = categoryService;
        this.tokenService = tokenService;
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> getCategories(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse) {
        try {
            if(tokenService.hasToken(headers,httpResponse)==null){
                throw new IllegalArgumentException("Invalid token");
            }
            return ResponseEntity.ok().body(new Success(categoryService.getAllCategories()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Object> getCategoryById(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer id) {
        try {
            if(tokenService.hasToken(headers,httpResponse)==null){
                throw new IllegalArgumentException("Invalid token");
            }
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                throw new IllegalArgumentException("Category not found");
            }
            return ResponseEntity.ok().body(new Success(category));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Object> updateCategory(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer id, @RequestBody Category category) {
        try {
            if(tokenService.hasToken(headers,httpResponse)==null){
                throw new IllegalArgumentException("Invalid token");
            }
            Category existingCategory = categoryService.getCategoryById(id);
            if (existingCategory == null) {
                throw new IllegalArgumentException("Category not found");
            }
            category.setId(existingCategory.getId());
            categoryService.updateCategory(category);
            return ResponseEntity.ok().body(new Success("Category updated"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Object> deleteCategory(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @PathVariable Integer id) {
        try {
            if(tokenService.hasToken(headers,httpResponse)==null){
                throw new IllegalArgumentException("Invalid token");
            }
            Category category = categoryService.getCategoryById(id);
            if (category == null) {
                throw new IllegalArgumentException("Category not found");
            }
            categoryService.deleteCategory(category);
            return ResponseEntity.ok().body(new Success("Category deleted"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Object> createCategory(@RequestHeader HttpHeaders headers, HttpServletResponse httpResponse, @RequestBody Category category) {
        try {
            if(tokenService.hasToken(headers,httpResponse)==null){
                throw new IllegalArgumentException("Invalid token");
            }
            categoryService.createCategory(category);
            return ResponseEntity.ok().body(new Success("Category created"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Error(400, e.getMessage()));
        }
    }
}
