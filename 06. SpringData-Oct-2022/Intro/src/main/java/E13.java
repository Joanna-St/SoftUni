import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class E13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String townToDelete = scanner.nextLine();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager.createQuery("select e from Employee e " +
                        "where e.address.town.name = :n", Employee.class)
                .setParameter("n", townToDelete)
                .getResultList();

        for (Employee e : employees) {
            e.setAddress(null);
            entityManager.persist(e);
        }

        List<Address> addresses = entityManager.createQuery("select a from Address a " +
                        "where a.town.name = :n", Address.class)
                .setParameter("n", townToDelete)
                .getResultList();

        int addressCount = addresses.size();

        for (Address a : addresses) {
            entityManager.remove(a);
        }


        Town toDelete = entityManager.createQuery("select t from Town t where t.name = :n", Town.class)
                .setParameter("n", townToDelete)
                        .getSingleResult();

        entityManager.remove(toDelete);

        System.out.printf("%d %s in %s deleted", addressCount, addressCount != 1 ? "addresses" : "address", townToDelete);

        entityManager.getTransaction().commit();
    }
}
