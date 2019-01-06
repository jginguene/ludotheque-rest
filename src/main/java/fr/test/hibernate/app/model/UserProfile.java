package fr.test.hibernate.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;


public enum  UserProfile {

   USER,
   ADMIN;



   public int getId(){
       return this.ordinal();
   }

}
