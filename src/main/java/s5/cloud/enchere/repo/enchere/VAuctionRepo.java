package s5.cloud.enchere.repo.enchere;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.enchere.VAuction;

public interface VAuctionRepo extends JpaRepository<VAuction, Integer>{
     @Query(value="SELECT CEIL(count(*)/10) as cont from v_auction_status", nativeQuery=true)
     public int countPage();

     public List<VAuction> findByStatus(Integer status, Pageable page);
}
