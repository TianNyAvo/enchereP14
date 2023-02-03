package s5.cloud.enchere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.model.notification.Notification;
import s5.cloud.enchere.model.notification.TokenFirebase;
import s5.cloud.enchere.mongorepo.TokenFirebaseRepo;
import s5.cloud.enchere.repo.NotificationRepo;
import s5.cloud.enchere.service.common.CrudService;

@Service
public class NotificationService extends CrudService<Notification, NotificationRepo> {
     @Autowired
     private FirebaseMessagingService firebaseMessagingService;

     @Autowired
     private TokenFirebaseRepo tokenFirebaseRepo;

     public NotificationService(NotificationRepo repo) {
          super(repo);
     }

     public List<Notification> findByCustomerId(Integer customerid) throws CustomException {
          List<Notification> liste = repo.findByCustomerIdAndEnvoye(customerid, false);

          return liste;
     }

     public List<Notification> findByCustomerIdFirebase(Integer customerid) throws CustomException {
          List<Notification> liste = repo.findByCustomerIdAndEnvoyeFirebase(customerid, false);
          return liste;
     }

     public void sendNotification(Integer customerid, String token) throws CustomException {
          updateTokenFirebase(customerid, token);
          List<Notification> liste = findByCustomerId(customerid);
          try {
               for (Notification n : liste) {
                    firebaseMessagingService.sendNotification("Enchère", n.getDescription(), token);
                    n.setEnvoye(true);
                    super.update(n);
               }
          } catch (Exception e) {
               throw new CustomException("Erreur lors de l'envoi de la notification " + e.getMessage());
          }
     }

     public void sendNotificationBackGround(Notification notif) throws CustomException {
          try {
               TokenFirebase token = tokenFirebaseRepo.findByUserId(notif.getCustomerId());
               if (token != null) {
                    firebaseMessagingService.sendNotification("Enchère", notif.getDescription(),
                              token.getTokenValue());
                    notif.setEnvoyeFirebase(true);
                    super.update(notif);
               }
          } catch (Exception e) {
               throw new CustomException("Erreur lors de l'envoi de la notification " + e.getMessage());
          }
     }

     private TokenFirebase updateTokenFirebase(Integer customerid, String token) {
          TokenFirebase tokenFirebase = tokenFirebaseRepo.findByUserId(customerid);
          if (tokenFirebase == null) {
               tokenFirebase = new TokenFirebase();
               tokenFirebase.setUserId(customerid);
               tokenFirebase.setTokenValue(token);
               tokenFirebaseRepo.save(tokenFirebase);
          } else {
               if (!tokenFirebase.getTokenValue().equals(token)) {
                    tokenFirebase.setTokenValue(token);
                    tokenFirebaseRepo.save(tokenFirebase);
               }
          }
          return tokenFirebase;
     }

     public void sendAllNotificationBackground() throws Exception {
          List<Notification> liste = repo.findByEnvoyeFirebase(false);
          for (Notification n : liste) {
               sendNotificationBackGround(n);
          }
     }

     @Scheduled(fixedRate = 60000)
     public void syncSqlite() {
          try {
               sendAllNotificationBackground();
               System.out.println("notif");
          } catch (Exception e) {
               e.printStackTrace();
          }
     }
}
