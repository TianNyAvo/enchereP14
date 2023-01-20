package s5.cloud.enchere.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Notification extends HasId{
     private String description;
     private Boolean envoye;
     @Column(name="customer_id")
     private Integer customerId;
}
