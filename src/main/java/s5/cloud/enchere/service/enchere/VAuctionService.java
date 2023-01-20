package s5.cloud.enchere.service.enchere;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import s5.cloud.enchere.model.enchere.VAuction;
import s5.cloud.enchere.repo.enchere.VAuctionRepo;
import s5.cloud.enchere.service.common.CrudService;
@Service
public class VAuctionService extends CrudService<VAuction,VAuctionRepo > {
     private static int size=10;
     public VAuctionService(VAuctionRepo repo) {
          super(repo);
     }
     public List<VAuction>findAll(int deb){
          return  this.repo.findAll(PageRequest.of(deb*10, 10)).getContent();
     }
     public int countPage(){
          return this.repo.countPage();
     }
     public List<VAuction>findEnCours(int deb){
          return this.repo.findByStatus(1, PageRequest.of(deb*10, 10));
     }
}
