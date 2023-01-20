package s5.cloud.enchere.model.login;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.LoginEntity;

@Entity
@Setter
@Getter
public class Users extends HasId implements LoginEntity{     
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


     @Override
     public String getPassword() {
          return password;
     }

     @Override
     public String getEmail() {
          return email;
     }

     public Users() {
     }
     public Users(Integer id) {
          setId(id);
     }
}
