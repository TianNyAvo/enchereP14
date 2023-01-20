package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.enchere.Category;
import s5.cloud.enchere.repo.backoffice.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Crud operations
    public void createCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public void deleteCategory(Category category) {
        this.categoryRepository.delete(category);
    }

    public Category getCategoryById(Integer id) {
        return this.categoryRepository.findById(id).orElse(null);
    }

    public Iterable<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

}
