package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Grade;
import lt.vu.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GradeDAO implements IGradeDAO {
    @Inject
    protected EntityManager em;

    public void create(Grade grade) {
        em.persist(grade);
    }
    public void updateAndFlush(Grade grade) {
        em.merge(grade);
        em.flush();
    }

    public List<Grade> getAllGrades() {
        return em.createNamedQuery("Grade.findAll", Grade.class).getResultList();
    }

    public Grade findById(Integer id) {
        return em.find(Grade.class, id);
    }

}
