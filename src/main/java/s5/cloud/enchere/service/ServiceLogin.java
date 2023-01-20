package s5.cloud.enchere.service;

import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.exception.LoginException;
import s5.cloud.enchere.model.LoginEntity;

public interface ServiceLogin <E extends LoginEntity> {
    E login(E entity) throws LoginException;
}
