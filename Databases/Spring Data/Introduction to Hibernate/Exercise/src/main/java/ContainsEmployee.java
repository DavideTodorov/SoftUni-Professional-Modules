import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class ContainsEmployee {

    public static void main(String[] args) {
        /*      3. Contains Employee       */

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        System.out.println("Enter name:");
        String nameInput = scanner.nextLine();

        em.getTransaction().begin();

        Query query = em.createNativeQuery("select * from soft_uni.employees AS e " +
                "WHERE CONCAT(e.first_name, ' ', e.last_name) " +
                "= :input");

        query.setParameter("input", nameInput);

        int i = query.getResultList().size();

        if (i > 0){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

        em.getTransaction().commit();
    }
}
