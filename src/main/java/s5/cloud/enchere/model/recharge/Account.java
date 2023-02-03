package s5.cloud.enchere.model.recharge;


import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Setter
@Getter
@Entity
public class Account extends HasId {
     @ManyToOne
     @JoinColumn(name="movement_type")
     private MovementType type;
     private Double amount;

     @Column(name="date_movement")
     private Timestamp dateMovement;

     @Column(name="administrator_id")
     private Integer administratorId;

     @Column(name="validation_date")
     private Timestamp validationDate;

     @Column(name="auction_id")
     private Integer auctionId;

     @ManyToOne
     @JoinColumn(name="customer_id")
     private Users customer;
   
     public void setAmount(Double am)throws Exception{
          if(am==null || am<0)
               throw new Exception("Amount must be positive");
          this.amount=am;
     }
     
}
