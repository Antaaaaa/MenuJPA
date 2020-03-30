package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {
    private EntityManagerFactory emFactory;
    private EntityManager em;

    public Util(){
        emFactory = Persistence.createEntityManagerFactory("MenuJPA");
        em = emFactory.createEntityManager();
    }
    public void close(){
        if (emFactory != null) emFactory.close();
        if (em != null) em.close();
    }
    public EntityManagerFactory getEmFactory() {
        return emFactory;
    }

    public EntityManager getEm() {
        return em;
    }
}
