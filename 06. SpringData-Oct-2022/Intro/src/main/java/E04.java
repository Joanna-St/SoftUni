import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E04 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<String > names = entityManager.createQuery("select firstName from Employee where salary > 50000", String.class).getResultList();

        names.forEach(System.out::println);

        entityManager.getTransaction().commit();
    }
}
