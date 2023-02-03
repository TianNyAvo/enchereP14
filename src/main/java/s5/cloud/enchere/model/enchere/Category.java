package s5.cloud.enchere.model.enchere;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasName;

@Entity
@Getter
@Setter
public class Category extends HasName {
     private String description;
}
