import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {

    public static void main(String[] args) {
        /*      13.	Remove Towns        */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        Town town = em.createQuery("select t from Town t where t.name = :input", Town.class)
                .setParameter("input", townName)
                .getSingleResult();

        List<Address> addresses = em.createQuery("select a from Address a where a.town.id = :_id", Address.class)
                .setParameter("_id", town.getId())
                .getResultList();

        int rowsAffected = addresses.size();

        em.getTransaction().begin();
        addresses.forEach(em::remove);
        em.getTransaction().commit();


        System.out.printf("%d address in %s deleted%n", rowsAffected, townName);


    }
}
