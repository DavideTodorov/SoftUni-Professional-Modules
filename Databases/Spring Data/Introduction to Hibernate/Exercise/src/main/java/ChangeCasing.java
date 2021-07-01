import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ChangeCasing {

    public static void main(String[] args) {
        /*      2. Change casing       */

        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("UPDATE Town t\n" +
                "SET t.name = UPPER(t.name)\n" +
                "WHERE length(t.name) >= 5");

        int i = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println(i);
    }
}
