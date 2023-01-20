package s5.cloud.enchere.model.enchere;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.util.FormatPhoto;
@Setter
@Getter
@Document("galerie")
public class Galerie {
     @Id
     private String _id;
     @Field("auction_id")
     private Integer auctionId;
     private String format;
     @Field("bytes")
     private String bytes;

     public void setFormat(String format)throws CustomException{
          for(String s:FormatPhoto.listeFormat){
               if(s.equals(format.toLowerCase())) this.format=s;
          }
          if(this.format==null){
               throw new CustomException("format photo non valide");
          }
     }
}
