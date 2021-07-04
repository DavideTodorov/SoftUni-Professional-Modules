import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // All tables from the tasks are created in one database, but the entities are separated in different packages

        EntityManager em = Persistence.createEntityManagerFactory("exercise_db").createEntityManager();

    }
}

