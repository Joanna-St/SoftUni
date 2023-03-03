import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        String inputLastName = scanner.nextLine();

        entityManager.getTransaction().begin();

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        entityManager.persist(newAddress);

        entityManager.createQuery("UPDATE Employee e SET e.address = :newAddress where e.lastName = :lastName")
                        .setParameter("newAddress", newAddress)
                        .setParameter("lastName", inputLastName)
                        .executeUpdate();

        entityManager.getTransaction().commit();
    }
}
