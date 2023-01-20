package s5.cloud.enchere.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.model.recharge.Account;
import s5.cloud.enchere.model.recharge.MovementType;
import s5.cloud.enchere.model.recharge.NAccount;
import s5.cloud.enchere.model.recharge.VAccountAvailable;
import s5.cloud.enchere.repo.account.AccountRepo;
import s5.cloud.enchere.repo.account.NAccountRepo;
import s5.cloud.enchere.repo.account.VAccountAvailableRepo;
import s5.cloud.enchere.service.common.CrudService;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class AccountService extends CrudService<Account, AccountRepo>{
     @Autowired
     private VAccountAvailableRepo vAccountAvailableRepo;

     @Autowired
     private NAccountRepo nAccountRepo;

     public AccountService(AccountRepo repo) {
          super(repo);
     }
     public Account recharger(Account account)throws CustomException {
          if(account.getCustomer()==null || account.getCustomer().getId()==null)
               throw new CustomException("Customer is required");
          account.setType(new MovementType(1));
          account.setDateMovement(Timestamp.valueOf(LocalDateTime.now()));
          account.setAdministratorId(null);
          account.setAuctionId(null);
          account.setValidationDate(null);
          return super.create(account);
     }
     public List<Account> historiqueValide(Integer customerId)throws CustomException {
          return repo.findByCustomerIdValid(customerId);
     }
     public VAccountAvailable stateAccount(Integer customerId)throws CustomException {
          return vAccountAvailableRepo.findByCustomerId(customerId);
     }

     

    public List<NAccount> getRefundRequests() throws CustomException {
        return nAccountRepo.getRefundRequests();
    }

    public void updateAccount(NAccount account) throws CustomException {
        nAccountRepo.save(account);
    }

    public void acceptRefundRequest(Integer admin_id, Integer adminId) {
        NAccount account = nAccountRepo.findById(adminId).get();
        account.setValidation_Date(Timestamp.valueOf(LocalDateTime.now()));
        account.setAdministrator_Id(admin_id);
        nAccountRepo.save(account);
    }

    public List<NAccount> getAcceptedRefundRequests() throws CustomException {
        return nAccountRepo.getAcceptedRefundRequests();
    }
}
