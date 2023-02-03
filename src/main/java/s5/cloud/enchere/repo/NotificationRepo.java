package s5.cloud.enchere.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.notification.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

     public List<Notification> findByCustomerIdAndEnvoye(Integer customer, Boolean envoye);

     public List<Notification> findByCustomerIdAndEnvoyeFirebase(Integer customer, Boolean envoye);

     public List<Notification> findByEnvoyeFirebase(Boolean envoye);

}
