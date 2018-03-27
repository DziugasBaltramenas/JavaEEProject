package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Grade;
import lt.vu.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GradeDAO {
    @Inject
    private EntityManager em;

    public void create(Grade grade) {
        em.persist(grade);
    }
    public void updateAndFlush(Grade grade) {
        em.merge(grade);
        em.flush();
    }
}
