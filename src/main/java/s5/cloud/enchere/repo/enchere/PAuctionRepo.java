
package s5.cloud.enchere.repo.enchere;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.enchere.PAuction;

public interface PAuctionRepo extends JpaRepository<PAuction, Integer>{
     @Query(value="SELECT * from v_participation where customer_id = ?", nativeQuery=true)
     public List<PAuction> getParticipationByIdUser(Integer customer_id);

     
}
