package s5.cloud.enchere.service.common;

import java.util.List;

public interface ServiceWithFK<E, FK> extends Service<E> {

    List<E> findForFK (FK fk);

}
