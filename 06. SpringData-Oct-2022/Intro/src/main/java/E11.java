import entities.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class E11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String exp = String.format("%s%%", scanner.nextLine());

        entityManager.getTransaction().begin();

        List<Employee> employees =
                entityManager.createQuery("select e from Employee e where upper(e.firstName) like :exp", Employee.class)
                .setParameter("exp", exp.toUpperCase(Locale.ROOT))
                        .getResultList();

        employees.forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n"
                , e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.getTransaction().commit();
    }
}
