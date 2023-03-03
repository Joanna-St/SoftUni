
import javax.persistence.*;
import java.util.Scanner;

public class E03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String inputName = scanner.nextLine();

        entityManager.getTransaction().begin();


        Long getCount = entityManager.createQuery("SELECT count(e) from Employee e where concat(e.firstName,' ',e.lastName) = :inputName"
                , Long.class).setParameter("inputName", inputName).getSingleResult();

        if (getCount > 0){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
    }
}
