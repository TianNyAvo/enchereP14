package s5.cloud.enchere.service.login;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.exception.LoginException;
import s5.cloud.enchere.model.login.RoleType;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.repo.login.UserRepo;
import s5.cloud.enchere.service.LoginService;
import s5.cloud.enchere.service.ServiceLogin;
import s5.cloud.enchere.service.common.CrudService;
import s5.cloud.enchere.util.BCrypt;

@Service
public class UserService extends CrudService<Users, UserRepo> implements ServiceLogin<Users> {

     public UserService(UserRepo repo) {
          super(repo);
     }

     @Override
     public Users login(Users entity) throws LoginException {
          try {
               entity.setPassword(BCrypt.hashPassword(entity.getPassword()));
          } catch (NoSuchAlgorithmException e) {
               throw new LoginException("Error in password");
          }
          Users founduser = repo.findByEmailAndPassword(entity.getEmail(), entity.getPassword());
          if (founduser == null) {
               throw new LoginException("Email or password is incorrect, person not found");
          }
          return founduser;
     }

    

     public Users inscriptionCustomer(Users entity) throws LoginException, CustomException, Exception {
          if (entity.getFirstname() == null || entity.getFirstname().equals("")) {
               throw new LoginException("First name is required");
          }
          if (entity.getLastname() == null || entity.getLastname().equals("")) {
               throw new LoginException("Last name is required");
          }
          if (entity.getPassword() == null || entity.getPassword().equals("")) {
               throw new LoginException("Password is required");
          }
          if (entity.getPhoneNumber() == null || entity.getPhoneNumber().equals("")) {
               throw new LoginException("Phone number is required");
          }
          if (entity.getBirthdate() == null) {
               throw new LoginException("Birthdate is required");
          }
          if (entity.getEmail() == null || entity.getEmail().equals("")) {
               throw new LoginException("Email is required");
          }
          // verifier l'email
          List<Users> liste = repo.findByEmail(BCrypt.hashPassword(entity.getEmail()));
          if (liste != null && liste.size() > 0) {
               throw new LoginException("Email already used");
          }
          entity.setPassword(BCrypt.hashPassword(entity.getPassword()));
          entity.setRole(new RoleType(2));
          entity.setJoinedDate(Timestamp.valueOf(LocalDateTime.now()));
          return create(entity);
     }



}
