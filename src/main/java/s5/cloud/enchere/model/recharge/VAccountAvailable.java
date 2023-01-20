package s5.cloud.enchere.model.recharge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.login.Users;

@Entity
@Setter
@Getter
@Table(name="v_account_available")
public class VAccountAvailable {
     @Id
     private Integer rows;
     @ManyToOne
     @JoinColumn(name="customer_id")
     private Users customer;
     
     @Column(name="money_current")
     private Double moneyCurrent;

     @Column(name="money_available")
     private Double moneyAvailable;

     @Column(name="money_blocked")
     private Double moneyBlocked;
}
