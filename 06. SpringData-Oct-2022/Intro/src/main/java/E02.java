import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Locale;

public class E02 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Town> resultList =
                entityManager.createQuery("select t from Town  t", Town.class).getResultList();

        for (Town town : resultList) {
            String name = town.getName();

            if (name.length() <= 5){
                town.setName(name.toUpperCase(Locale.ROOT));

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
    }
}
