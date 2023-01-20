package s5.cloud.enchere.repo.enchere;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.enchere.Auction;

public interface AuctionRepo extends JpaRepository<Auction, Integer>{
     @Query(value="SELECT check_auction() ", nativeQuery=true)
     public void checkAuction();

     @Query(value="SELECT value from settings WHERE id=2", nativeQuery=true)
     public Double getDurationAuction();
}
