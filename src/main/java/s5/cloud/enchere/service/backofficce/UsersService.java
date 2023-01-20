package s5.cloud.enchere.service.backofficce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.repo.backoffice.UsersRepository;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    private UsersRepository repo;

    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    public Users findAdminByEmailAndPassword(String email, String password) {
        return repo.findAdminByEmailAndPassword(email, password);
    }

    public Users saveAdmin(Users admin) {
        return repo.save(admin);
    }
}
