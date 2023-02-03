package s5.cloud.enchere.mongorepo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import s5.cloud.enchere.model.notification.TokenFirebase;

@Repository
public interface TokenFirebaseRepo extends MongoRepository<TokenFirebase, String> {
     public TokenFirebase findByUserId(Integer id);

}
