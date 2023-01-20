package s5.cloud.enchere.model.login;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;

import java.sql.Timestamp;
import java.util.Date;

@Entity(name="token")
@Getter
@Setter

public class Token extends HasId{
    @ManyToOne
    private Users users;
    private String value;
    private Date expiration_date;
    private Date creation_date;
    @Transient
    public static final int EXPIRATION = 1000*60*60 * 24;   
}
