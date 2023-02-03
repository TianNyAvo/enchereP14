package s5.cloud.enchere.model.enchere;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Entity
@Getter
@Setter
@Table(name = "v_auction_status")
public class VAuction extends HasId{
     private Timestamp start_date;

     private Timestamp end_date;

     private String title;

     private String description;

     @ManyToOne
     private Category category;

     @Column(name="min_price")
     private Double minPrice;

     @ManyToOne
     private Users seller;
     private Integer status;
     @Transient
     private String statusName;

     public String getStatusName() {
          if (statusName == null) {
               if (status == 1) {
                    statusName = "en cours";
               } else {
                    statusName = "fini";
               }
          }
          return statusName;
     }

}
