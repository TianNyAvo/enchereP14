package s5.cloud.enchere.repo;

import java.util.List;

import s5.cloud.enchere.model.LoginEntity;

public interface LoginRepo<E extends LoginEntity> {
    List<E> findByEmail (String email);
}
