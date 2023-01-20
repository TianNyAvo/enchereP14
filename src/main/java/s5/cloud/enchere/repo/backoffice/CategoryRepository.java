package s5.cloud.enchere.repo.backoffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s5.cloud.enchere.model.enchere.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
