
package s5.cloud.enchere.repo.enchere;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.enchere.HAuction;

public interface HAuctionRepo extends JpaRepository<HAuction, Integer>{
     @Query(value="SELECT * from v_gain where seller_id = ?", nativeQuery=true)
     public List<HAuction> getHistoryByIdUser(Integer id);

     
}
