import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindLatest10Projects {

    public static void main(String[] args) {
        /*      9. Find Latest 10 Projects     */

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        List<Project> results = em.createQuery("select p " +
                "from Project p " +
                "order by p.name asc, p.startDate desc ", Project.class)
                .setMaxResults(10)
                .getResultList();

        for (Project p : results) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            //Start date
            String startDateFormatted;
            if (p.getStartDate() == null) {
                startDateFormatted = "null";
            } else {
                LocalDateTime date = p.getStartDate();
                startDateFormatted = date.format(formatter);
            }


            //End date
            String endDateFormatted;
            if (p.getEndDate() == null) {
                endDateFormatted = "null";
            } else {
                LocalDateTime date = p.getEndDate();
                endDateFormatted = date.format(formatter);
            }

            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s\n" +
                    " \tProject Start Date:%s\n" +
                    " \tProject End Date: %s%n", p.getName(), p.getDescription(), startDateFormatted, endDateFormatted);
        }
    }
}
