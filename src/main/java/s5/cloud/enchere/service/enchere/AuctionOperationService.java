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
          Auction auction=auctionrepo.findById(auc.getAuctionId()).orElseThrow(()->new CustomException("L'enchère non trouvée"));
          if(auction.getEndDate().before(timestamp)) throw new CustomException("L'enchère est terminée");

          //verifier que l'utilisateur n'est pas propriétaire de l'enchere
          if(auction.getSeller().getId()==auc.getCustomer().getId()) throw new CustomException("Vous ne pouvez pas encherir sur votre produit");

          //verifier que son compte peut rencherir, ampy vola
          VAccountAvailable vAccountAvailable=accountService.stateAccount(auc.getCustomer().getId());
          if(vAccountAvailable.getMoneyAvailable()<auc.getAmount()) throw new CustomException("Fonds insuffisants");
          
          //verifier que heure est en dessous de heure de fin et que son argent est au dessu du celui d'apres
          AuctionOperation last=this.repo.findLastOffer(auc.getAuctionId());
          if(last!=null) {
               if(last.getAmount()>=auc.getAmount()) throw new CustomException("Votre enchère doit être plus élevé que la précédente");
          }else{
               if(auction.getMinPrice()>=auc.getAmount()) throw new CustomException("Votre enchère doit être plus élevé que l'enchère de départ");
          }
          auc.setOperationDate(timestamp);
          return super.create(auc);
     }
}
