package s5.cloud.enchere.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@MappedSuperclass
public class HasId {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Integer id;
    
}
