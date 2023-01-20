package s5.cloud.enchere.model.enchere;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Entity
@Setter
@Getter
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Auction extends HasId {
     @Column(name="start_date")
     private Timestamp startDate;

     @Column(name="end_date")
     private Timestamp endDate;

     private String title;

     private String description;

     @ManyToOne
     private Category category;

     @Column(name="min_price")
     private Double minPrice;

     @ManyToOne
     private Users seller;

     @Transient
     private List<AuctionOperation> operation;

     @Transient
     @JsonProperty(value="galerie")
     private List<Galerie>galerie;

}
