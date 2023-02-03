package s5.cloud.enchere.model.recharge;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Setter
@Getter
@Entity
@Table(name = "account")
public class NAccount extends HasId {
     @ManyToOne
     @JoinColumn(name = "movement_type")
     private MovementType type;
     private Double amount;
     private Timestamp date_movement;
     private Integer administrator_Id;
     private Timestamp validation_Date;
     private Integer auction_Id;
     private Integer customer_Id;

     public void setAmount(Double am) throws Exception {
          if (am == null || am < 0)
               throw new Exception("Amount must be positive");
          this.amount = am;
     }

}
