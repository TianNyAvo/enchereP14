package s5.cloud.enchere.service.enchere;

import java.util.List;
import org.hibernate.Hibernate;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.enchere.Auction;

import s5.cloud.enchere.model.enchere.PAuction;
import s5.cloud.enchere.repo.enchere.PAuctionRepo;
import s5.cloud.enchere.service.common.CrudService;
@Service
public class PAuctionService extends CrudService<PAuction,PAuctionRepo> {
     private static int size=10;
     public PAuctionService(PAuctionRepo repo) {
          super(repo);
     }
     public List<PAuction>getParticipationByIdUser(int id){
          List<PAuction> list = this.repo.getParticipationByIdUser(id);
          
          for(PAuction auc:list){
               //Hibernate.initialize(auc.getCustomer());
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
