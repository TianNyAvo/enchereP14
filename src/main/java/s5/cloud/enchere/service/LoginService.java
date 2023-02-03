package s5.cloud.enchere.service;

import org.springframework.stereotype.Service;

import s5.cloud.enchere.exception.LoginException;
import s5.cloud.enchere.model.LoginEntity;
import s5.cloud.enchere.repo.LoginRepo;

import java.util.List;

public class LoginService<E extends LoginEntity, R extends LoginRepo<E>> implements ServiceLogin<E> {

    protected R repo;

    public LoginService(R repo) {
        this.repo = repo;
    }

    public E login(E entity) throws LoginException {
        return login(entity, repo);
    }

    public static  <E extends LoginEntity, R extends LoginRepo<E>> E login(E entity, R repo) throws LoginException {
        List<E> list = repo.findByEmail(entity.getEmail());
        if (list.size() == 0) throw new LoginException("user not found");
        for (E elt : list) {
            if (elt.getPassword().equals(entity.getPassword())) {
                return elt;
            }
        }
        throw new LoginException("wrong password");
    }

}
