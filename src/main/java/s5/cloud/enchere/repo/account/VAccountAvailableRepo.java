package s5.cloud.enchere.repo.account;

import org.springframework.data.jpa.repository.JpaRepository;

import s5.cloud.enchere.model.recharge.VAccountAvailable;

public interface VAccountAvailableRepo extends JpaRepository<VAccountAvailable, Integer>{
     public VAccountAvailable findByCustomerId(Integer customerId);
}
