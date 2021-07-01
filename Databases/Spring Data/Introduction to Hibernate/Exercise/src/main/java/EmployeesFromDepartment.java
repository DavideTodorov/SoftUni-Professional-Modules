import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment {

    public static void main(String[] args) {
        /*      5. Employees from Department       */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("select concat(e.firstName, ' ',e.lastName, ' from ', e.department.name, ' - $', e.salary)" +
                "  from Employee AS e where e.department.name = 'Research and Development'" +
                "ORDER BY e.salary asc, e.id asc")
                .getResultList()
                .forEach(System.out::println);

        em.getTransaction().commit();
    }
}
