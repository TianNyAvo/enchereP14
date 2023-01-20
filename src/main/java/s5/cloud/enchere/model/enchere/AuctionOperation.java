package s5.cloud.enchere.model.enchere;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Entity
@Getter
@Setter
@Table(name="auction_operation")
public class AuctionOperation extends HasId{
     @ManyToOne
     private Users customer;
     private Double amount;
     @Column(name="operation_date")
     private Timestamp operationDate;

     @Column(name="auction_id")
     private Integer auctionId;

     
}
