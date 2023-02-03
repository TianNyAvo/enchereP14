package s5.cloud.enchere.model.notification;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document("token_firebase")
public class TokenFirebase {
     @Id
     private String _id;

     @Field("user_id")
     private Integer userId;

     @Field("token_value")
     private String tokenValue;
}
