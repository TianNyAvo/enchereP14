package s5.cloud.enchere.repo.backoffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import s5.cloud.enchere.model.backoffice.V_RPP;

import java.util.List;

@Repository
public interface V_RppRepository extends JpaRepository<V_RPP, Integer> {

    @Query(value = "SELECT total_commission,year,month FROM v_recette_par_periode WHERE year = ?1", nativeQuery = true)
    List<Object> getRecetteAnnee(Integer annee);
}
