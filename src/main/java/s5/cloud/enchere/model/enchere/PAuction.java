/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s5.cloud.enchere.model.enchere;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import s5.cloud.enchere.model.HasId;
import s5.cloud.enchere.model.login.Users;

@Entity
@Getter
@Setter
/**
 *
 * @author TianNyAvo
 */
public class PAuction extends HasId
{
    @ManyToOne
    private Users customer;
    
    private double amount;
    
    private Timestamp operation_date;
    
    private Timestamp start_date;

     private Timestamp end_date;

     private String title;

     private String description;
     
     @ManyToOne
     private Category category;

     private Double min_price;

     @ManyToOne
     private Users seller;
     private Integer status;
     
}
