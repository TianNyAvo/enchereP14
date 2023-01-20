package s5.cloud.enchere.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import s5.cloud.enchere.model.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Integer>{
     public List<Notification>findByCustomerIdAndEnvoye(Integer customer, Boolean envoye);
}
