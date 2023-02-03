package s5.cloud.enchere.repo.enchere;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.enchere.AuctionOperation;

public interface AuctionOperationRepo extends JpaRepository<AuctionOperation, Integer>{
     
     public List<AuctionOperation>findByAuctionIdOrderByOperationDateDesc(Integer auctionid);

     @Query(value="SELECT * from auction_operation WHERE auction_id = ?1 ORDER BY operation_date DESC LIMIT 1", nativeQuery = true)
     public AuctionOperation findLastOffer(Integer auctionid);
}
