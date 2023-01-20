package s5.cloud.enchere.service;

import java.util.List;

import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.model.Notification;
import s5.cloud.enchere.repo.NotificationRepo;
import s5.cloud.enchere.service.common.CrudService;

@Service
public class NotificationService extends CrudService<Notification, NotificationRepo>{

     public NotificationService(NotificationRepo repo) {
          super(repo);
     }
     public List<Notification>findByCustomerId(Integer customerid)throws CustomException{
          List<Notification>liste= repo.findByCustomerIdAndEnvoye(customerid, false);
          for(Notification n:liste){
               n.setEnvoye(true);
          }
          super.saveAll(liste);
          return liste;
     }
}
