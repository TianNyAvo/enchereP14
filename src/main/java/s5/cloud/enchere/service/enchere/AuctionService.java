package s5.cloud.enchere.service.enchere;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import s5.cloud.enchere.exception.CustomException;
import s5.cloud.enchere.model.enchere.Auction;
import s5.cloud.enchere.model.enchere.Galerie;
import s5.cloud.enchere.model.enchere.RechercheAvance;
import s5.cloud.enchere.model.login.Users;
import s5.cloud.enchere.mongorepo.GalerieRepo;
import s5.cloud.enchere.repo.enchere.AuctionOperationRepo;
import s5.cloud.enchere.repo.enchere.AuctionRepo;
import s5.cloud.enchere.service.common.CrudService;
import s5.cloud.enchere.service.login.UserService;

@Service
public class AuctionService extends CrudService<Auction, AuctionRepo> {
     @Autowired
     private GalerieRepo galerieRepo;
     @Autowired
     private AuctionOperationRepo auctionOrepo;

     @Autowired
     private UserService userService;

     @Autowired
     private EntityManager entityManager;

     public AuctionService(AuctionRepo repo) {
          super(repo);
     }

     public Auction findById(Integer id) {
          Auction auc = super.findById(id);
          auc.setOperation(auctionOrepo.findByAuctionIdOrderByOperationDateDesc(id));
          System.out.println(auc.getOperation().size());
          auc.setGalerie(listePhoto(id));
          return auc;
     }

     public List<Auction> findByCriteria(RechercheAvance criteria, int page) {
          Query query = entityManager.createNativeQuery(criteria.getSql(), Auction.class);
          query.setFirstResult(page * 10);
          query.setMaxResults(10);
          List<Auction> liste = query.getResultList();
          for (Auction auc : liste) {
               Hibernate.initialize(auc.getCategory());
               Hibernate.initialize(auc.getSeller());
               /*
                * Hibernate.unproxy(auc.getCategory());
                * Hibernate.unproxy(auc.getSeller());
                * System.out.println(auc.getCategory().getName());
                * System.out.println(auc.getSeller().getFirstname());
                */
          }
          return liste;
     }

     public Integer countByCriteria(RechercheAvance criteria) {
          Query query = entityManager.createNativeQuery(
                    "SELECT CEIL(count(t.*)/10.0) as cont FROM (" + criteria.getSql() + ") t",
                    Integer.class);
          return (Integer) query.getSingleResult();
     }

     public List<Galerie> listePhoto(Integer id) {
          return galerieRepo.findByAuctionId(id);
     }

     @Scheduled(fixedRate = 60000)
     public void syncSqlite() {
          try {
               repo.checkAuction();
               System.out.println("mis a jour");
          } catch (Exception e) {
               e.printStackTrace();
          }
     }

     public Auction insert(Auction entity) throws CustomException {
          Double duree = repo.getDurationAuction();
          long vduree = Math.round(duree);
          if (entity.getCategory() == null || entity.getCategory().getId() == null) {
               throw new CustomException("category must not null");
          }
          if (entity.getTitle() == null || entity.getTitle().isEmpty()) {
               throw new CustomException("title must not null");
          }
          if (entity.getDescription() == null || entity.getDescription().isEmpty()) {
               throw new CustomException("description must not null");
          }
          if (entity.getMinPrice() == null) {
               entity.setMinPrice(0.00);
          }
          Instant instant = entity.getStartDate().toInstant();
          LocalDateTime time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).plusHours(vduree);
          entity.setEndDate(Timestamp.valueOf(time));

          Auction cre = create(entity);
          if (cre.getGalerie() != null) {
               for (Galerie g : entity.getGalerie()) {
                    g.setAuctionId(cre.getId());
               }
               galerieRepo.insert(cre.getGalerie());
          }
          return cre;
     }

     public Auction addPhoto(List<Galerie> galerie, Integer auctionId) throws CustomException {
          Auction auc = findById(auctionId);
          if (auc == null) {
               throw new CustomException("auction not found");
          }
          for (Galerie g : galerie) {
               g.setAuctionId(auctionId);
          }
          galerieRepo.insert(galerie);
          return findById(auctionId);
     }

     /*
      * public List<MAuction> findPageable(int deb){ //liste des enchères
      * return mauctionRepo.findAllByOrderByStartDateDesc(null);
      * }
      * public MAuction mFindAuction(int id){
      * return mauctionRepo.findByAId(id);
      * }
      */

}
