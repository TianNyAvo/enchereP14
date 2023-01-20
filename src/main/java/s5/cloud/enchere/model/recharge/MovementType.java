package s5.cloud.enchere.model.recharge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.HasName;

@Entity
@Getter
@Setter
@Table(name="movement_type")
public class MovementType extends HasId{
     @Column(name="name")
     protected String name;
     public MovementType() {
          super();
     }
     public MovementType(Integer id) {
          setId(id);
     }
}
