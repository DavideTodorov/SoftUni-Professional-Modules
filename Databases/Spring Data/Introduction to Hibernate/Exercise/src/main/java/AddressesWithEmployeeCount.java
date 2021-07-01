import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AddressesWithEmployeeCount {

    public static void main(String[] args) {
        /*      7. Addresses with Employee Count       */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();


        Query query = em.createQuery("select e.address.text, e.address.town.name, count(e.id) as num_of_empl " +
                "from Employee e " +
                "group by e.address.id " +
                "order by num_of_empl desc").setMaxResults(10);

        List<Object[]> resultList = query.getResultList();

        for (Object[] o : resultList) {
            System.out.println(String.format("%s, %s - %d employees", o[0], o[1], o[2]));
        }
    }
}
