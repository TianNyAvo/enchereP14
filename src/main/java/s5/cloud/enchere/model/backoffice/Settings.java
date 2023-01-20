package s5.cloud.enchere.model.backoffice;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;

@Entity
@Getter
@Setter
public class Settings extends HasId {
    private String name;
    private Double value;
}
