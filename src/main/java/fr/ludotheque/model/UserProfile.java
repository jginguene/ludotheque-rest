package fr.ludotheque.model;


public enum  UserProfile {

   USER,
   ADMIN;



   public int getId(){
       return this.ordinal();
   }

}
