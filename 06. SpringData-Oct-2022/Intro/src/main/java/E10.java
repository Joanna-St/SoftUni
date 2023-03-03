import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class E10 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createQuery("select e from Employee e " +
                                "where e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')"
                        , Employee.class)
                .getResultList();

        for (Employee e : employees) {
            e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));

            entityManager.persist(e);

            System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
        }

        entityManager.getTransaction().commit();
    }
}
