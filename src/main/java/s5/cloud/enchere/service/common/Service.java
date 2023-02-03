package s5.cloud.enchere.service.common;

import java.util.List;

import s5.cloud.enchere.exception.CustomException;

public interface Service <E> {

    E create(E obj) throws CustomException;

    E update(E obj) throws CustomException;

    void delete(Integer id);

    E findById(Integer id);

    Iterable<E> findAll();
    List<E> saveAll(List<E> obj);

}
