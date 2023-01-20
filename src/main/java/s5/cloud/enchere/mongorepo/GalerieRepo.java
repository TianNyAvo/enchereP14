package s5.cloud.enchere.mongorepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import s5.cloud.enchere.model.enchere.Galerie;

@Repository
public interface GalerieRepo extends MongoRepository<Galerie, String>{

     public List<Galerie>findByAuctionId(Integer id);
}
