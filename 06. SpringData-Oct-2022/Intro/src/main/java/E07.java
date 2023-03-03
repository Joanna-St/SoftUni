import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E07 {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Address> allAddresses = entityManager.createQuery("SELECT a from Address a " +
                "order by a.employees.size DESC", Address.class).setMaxResults(10).getResultList();

        for (Address a : allAddresses) {
            System.out.printf("%s, %s = %d employees%n"
                    , a.getText(),a.getTown().getName(),a.getEmployees().size());
        }

        entityManager.getTransaction().commit();
    }
}
