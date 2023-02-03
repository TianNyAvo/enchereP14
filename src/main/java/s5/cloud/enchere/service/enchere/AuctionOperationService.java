package s5.cloud.enchere.service.enchere;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.model.enchere.Auction;
import s5.cloud.enchere.model.enchere.AuctionOperation;
import s5.cloud.enchere.model.recharge.VAccountAvailable;
import s5.cloud.enchere.repo.enchere.AuctionOperationRepo;
import s5.cloud.enchere.repo.enchere.AuctionRepo;
import s5.cloud.enchere.service.AccountService;
import s5.cloud.enchere.service.common.CrudService;

@Service
public class AuctionOperationService extends CrudService<AuctionOperation,AuctionOperationRepo >{
     @Autowired
     private AuctionRepo auctionrepo;     

     @Autowired
     private AccountService accountService;

     public AuctionOperationService(AuctionOperationRepo repo) {
          super(repo);
     }
     @Override //rencherir
     public AuctionOperation create(AuctionOperation auc) throws CustomException{
          Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
          //verfier que Auctio exist
          Auction auction=auctionrepo.findById(auc.getAuctionId()).orElseThrow(()->new CustomException("Auction not found"));
          if(auction.getEndDate().before(timestamp)) throw new CustomException("Auction is closed");

          //verifier que l'utilisateur n'est pas propriétaire de l'enchere
          if(auction.getSeller().getId()==auc.getCustomer().getId()) throw new CustomException("You can't offer a price in your product");

          //verifier que son compte peut rencherir, ampy vola
          VAccountAvailable vAccountAvailable=accountService.stateAccount(auc.getCustomer().getId());
          if(vAccountAvailable.getMoneyAvailable()<auc.getAmount()) throw new CustomException("You don't have enough money");
          
          //verifier que heure est en dessous de heure de fin et que son argent est au dessu du celui d'apres
          AuctionOperation last=this.repo.findLastOffer(auc.getAuctionId());
          if(last!=null) {
               if(last.getAmount()>=auc.getAmount()) throw new CustomException("You must offer a price higher than the last offer");
          }else{
               if(auction.getMinPrice()>=auc.getAmount()) throw new CustomException("You must offer a price higher than the start price");
          }
          auc.setOperationDate(timestamp);
          return super.create(auc);
     }
}
