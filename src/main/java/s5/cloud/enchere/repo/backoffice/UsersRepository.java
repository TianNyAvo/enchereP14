package s5.cloud.enchere.repo.backoffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import s5.cloud.enchere.model.login.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query(value = "SELECT * FROM users WHERE email = ?1 AND password = ?2 AND role_type_id=1 ", nativeQuery = true)
    Users findAdminByEmailAndPassword(String email, String password);
}
