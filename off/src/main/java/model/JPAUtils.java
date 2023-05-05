package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {
   private static JPAUtils INSTANCE = new JPAUtils();

   private JPAUtils(){}
   public static JPAUtils getInstance(){
    return INSTANCE;
   }

   private final static EntityManagerFactory  EMF = Persistence.createEntityManagerFactory("Off");
   private final static EntityManager EM = EMF.createEntityManager();

   public EntityManager getEntityManager(){
    return EM;
   };
}