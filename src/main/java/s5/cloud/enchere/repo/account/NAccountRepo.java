package s5.cloud.enchere.repo.account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import s5.cloud.enchere.model.recharge.NAccount;

public interface NAccountRepo extends JpaRepository<NAccount, Integer>{
     @Query(value="SELECT * from account WHERE customer_id = ?1 AND validation_date is not null ORDER BY date_movement DESC", nativeQuery = true)
     public List<NAccount> findByCustomerIdValid(Integer customerId);

     @Query(value="SELECT * from account WHERE validation_date is null and movement_type=1 ORDER BY date_movement DESC", nativeQuery = true)
     public List<NAccount> getRefundRequests();

     @Query(value="SELECT * from account WHERE validation_date is not null and movement_type=1 ORDER BY date_movement DESC", nativeQuery = true)
     public List<NAccount> getAcceptedRefundRequests();
}
