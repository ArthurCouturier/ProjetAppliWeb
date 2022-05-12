package main.java;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import main.java.mainClasses.*;

@Singleton
public class Facade {

    private int idU;

    @PersistenceContext
    private EntityManager em;

    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){
        idU = 0;
    }

    public void main(String args[])  {

    }
}
