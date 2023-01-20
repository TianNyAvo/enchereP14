package s5.cloud.enchere.repo.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.repo.LoginRepo;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>, LoginRepo<Users>{
     public Users findByEmailAndPassword(String email, String password);
}
