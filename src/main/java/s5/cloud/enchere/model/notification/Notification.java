package s5.cloud.enchere.model.notification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;

@Entity
@Getter
@Setter
public class Notification extends HasId {
     private String description;
     private Boolean envoye;
     @Column(name = "customer_id")
     private Integer customerId;

     @Column(name = "envoye_firebase")
     private Boolean envoyeFirebase;
}
