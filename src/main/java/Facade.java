package main.java;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
public class Facade {

    @PersistenceContext
    private EntityManager em;

    //TypedQuery<User> req = em.createQuery();
    //private Collection<User> users = req.getResultList();

    public Facade(){}
}
