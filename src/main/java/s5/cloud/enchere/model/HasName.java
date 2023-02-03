package s5.cloud.enchere.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class HasName extends HasId {

    @Column
    protected String name;

}
