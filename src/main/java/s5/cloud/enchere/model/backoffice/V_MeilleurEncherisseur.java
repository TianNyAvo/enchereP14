package s5.cloud.enchere.model.backoffice;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.RoleType;
import s5.cloud.enchere.model.login.Users;

@Entity
@Getter
@Setter
@Table(name="v_meilleur_enrichisseur")
public class V_MeilleurEncherisseur extends HasId {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     @ManyToOne
     @JoinColumn(name="role_type_id")
     private RoleType role;

     private String firstname;

     private String lastname;

     private String email;

     private Date birthdate;

     @Column(name="phone_number")
     private String phoneNumber;

     @Column(name="joined_date")
     private Timestamp joinedDate;
     
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private String password;
     
    private Double commission;
}
