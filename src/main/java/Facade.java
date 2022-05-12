import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mainClasses.*;

@Singleton
public class Facade {

    private int idU;

    @PersistenceContext
    private EntityManager em;
    TypedQuery<User> req = em.createQuery("select u from User u", User.class);
    private Collection<User> users = req.getResultList();

    public Facade(){
        idU = 0;
    }

    public void addUser(User u) {
        users.put(idU, u); idU++;
    }

    public User getUser(int i) {
        return users.get(i);
    }

    public void main(String args[])  {

    }
}
