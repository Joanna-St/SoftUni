import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.*;

public class E12 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Department> allDepartments =
                entityManager.createQuery("SELECT d from Department d", Department.class).getResultList();

        Map<String, BigDecimal> departmentSalaries = new LinkedHashMap<>();

        for (Department d : allDepartments) {
            String departmentName = d.getName();

            BigDecimal maxSalary = d.getEmployees()
                    .stream()
                    .max(Comparator.comparing(Employee::getSalary))
                    .get()
                    .getSalary();

            if (maxSalary.compareTo(BigDecimal.valueOf(30000)) > 0 || maxSalary.compareTo(BigDecimal.valueOf(50000)) > 0) {
                departmentSalaries.put(departmentName, maxSalary);
            }
        }

        departmentSalaries.forEach((key, value) -> System.out.printf("%s %.2f%n", key, value));

        entityManager.getTransaction().commit();
    }
}
