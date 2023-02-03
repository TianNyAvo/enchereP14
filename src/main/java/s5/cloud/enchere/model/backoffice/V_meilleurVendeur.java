package s5.cloud.enchere.model.backoffice;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class V_meilleurVendeur extends V_MeilleurEncherisseur {
    private double nombreenchere;
}
