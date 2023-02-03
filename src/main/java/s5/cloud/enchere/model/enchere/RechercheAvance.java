package s5.cloud.enchere.model.enchere;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RechercheAvance {
     private String keyword;
     private Date startDate;
     private Date endDate;
     private Double minPrice;
     private Double maxPrice;
     private Integer[]category;
     private Integer status;//1 en cours, 2 fini

     public String getSql(){
          String sql = "select * from auction where 1=1";
          if(keyword != null && !keyword.isEmpty()){
               sql += " and (title like '%"+keyword+"%' or description like '%"+keyword+"%')";
          }
          if(startDate != null){
               sql += " and start_date >= '"+startDate+"'";
          }
          if(endDate != null){
               sql += " and start_date <= '"+endDate+"'";
          }
          if(minPrice != null){
               sql += " and min_price >= "+minPrice;
          }
          if(maxPrice != null){
               sql += " and min_price <= "+maxPrice;
          }
          if(category != null && category.length > 0){
               sql += " and category_id in (";
               for(int i = 0; i < category.length; i++){
                    sql += category[i];
                    if(i < category.length - 1){
                         sql += ",";
                    }
               }
               sql += ")";
          }
          if(status != null){
               if(status == 1){
                    sql += " and end_date >= now()";
               }else if(status == 2){
                    sql += " and end_date < now()";
               }
          }
          return sql;
     }
}
