import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E05 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees =
                entityManager.createQuery("SELECT e from Employee e " +
                        "where e.department.name = 'Research and Development' " +
                        "order by e.salary, e.id", Employee.class).getResultList();

        for (Employee e : employees) {
            System.out.printf("%s %s from %s - $%.2f%n",e.getFirstName(),e.getLastName(),e.getDepartment().getName(),e.getSalary());
        }

        entityManager.getTransaction().commit();
    }
}
