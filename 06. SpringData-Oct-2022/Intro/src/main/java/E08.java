import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        int id = scanner.nextInt();

        entityManager.getTransaction().begin();

        Employee e =
                entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class)
                        .setParameter("id", id)
                        .getSingleResult();

        System.out.printf("%s %s - %s%n",e.getFirstName(),e.getLastName(),e.getJobTitle());

        e.getProjects().stream().map(Project::getName).sorted(String::compareTo).forEach(s -> System.out.printf("   %s%n",s));

        entityManager.getTransaction().commit();
    }
}
