package s5.cloud.enchere.model.backoffice;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;

@Entity
@Getter
@Setter
public class V_RPP extends HasId {
    private Double total_commission;
    private Integer year;
    private Integer month;
}
