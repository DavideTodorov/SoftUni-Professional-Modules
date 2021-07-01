import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {

    public static void main(String[] args) {
        /*      6. Adding a New Address and Updating Employee      */

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        String lastNameInput = scanner.nextLine();

        Address vitoshka15 = new Address();
        vitoshka15.setText("Vitoshka 15");
        em.persist(vitoshka15);


        Employee employee = em.createQuery("SELECT e FROM Employee e where e.lastName = :lName", Employee.class)
                .setParameter("lName", lastNameInput).getSingleResult();

        employee.setAddress(vitoshka15);


        em.getTransaction().commit();
    }
}
