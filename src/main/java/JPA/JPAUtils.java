package JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nickl
 */
public class JPAUtils {
    
    EntityManagerFactory emf;
    
    public JPAUtils(){
        emf = Persistence.createEntityManagerFactory("AirlinePU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    
    
    
}