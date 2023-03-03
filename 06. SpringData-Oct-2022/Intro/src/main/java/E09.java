import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.List;

public class E09 {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> recentProjects = entityManager.createQuery("select p from Project p order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList();

        recentProjects.stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.printf("Project name: %s\n" +
                                " \tProject Description: %s\n" +
                                " \tProject Start Date: %s\n" +
                                " \tProject End Date: %s\n"
                        , p.getName()
                        , p.getDescription()
                        , p.getStartDate()
                        , p.getEndDate()));

        entityManager.getTransaction().commit();
    }
}
