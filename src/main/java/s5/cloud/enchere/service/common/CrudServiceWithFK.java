package s5.cloud.enchere.service.common;

import org.springframework.data.jpa.repository.JpaRepository;
/*
*
*
* */
public abstract class CrudServiceWithFK<E, FK,  R extends JpaRepository<E, Integer> > extends CrudService<E, R> implements ServiceWithFK<E, FK> {
    public CrudServiceWithFK(R repo) {
        super(repo);
    }
}
