package s5.cloud.enchere.model.login;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasName;

@Entity
@Getter
@Setter
@Table(name="role_type")
public class RoleType extends HasName{

     public RoleType() {
     }
     public RoleType(Integer id){
          setId(id);
     }
}
