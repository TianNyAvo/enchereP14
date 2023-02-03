package s5.cloud.enchere.service.enchere;

import java.util.List;
import org.hibernate.Hibernate;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.enchere.Auction;

import s5.cloud.enchere.model.enchere.HAuction;
import s5.cloud.enchere.repo.enchere.HAuctionRepo;
import s5.cloud.enchere.service.common.CrudService;
@Service
public class HAuctionService extends CrudService<HAuction,HAuctionRepo> {
     private static int size=10;
     public HAuctionService(HAuctionRepo repo) {
          super(repo);
     }
     public List<HAuction>getHistoryByIdUser(int id){
          List<HAuction> list = this.repo.getHistoryByIdUser(id);
          
          for(HAuction auc:list){
               Hibernate.initialize(auc.getCategory());
               Hibernate.initialize(auc.getSeller());
               /*Hibernate.unproxy(auc.getCategory());
               Hibernate.unproxy(auc.getSeller());
               System.out.println(auc.getCategory().getName());
               System.out.println(auc.getSeller().getFirstname());*/
          }
          return list;
     }
     
}
