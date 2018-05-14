package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudentDAO {
    @Inject
    protected EntityManager em;

    public void create(Student student) {
        em.persist(student);
    }
    public void updateAndFlush(Student student) {
        em.merge(student);
        em.flush();
    }

    public List<Student> getAllStudents() {
        return em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    public Student findByLastName(String lastName) {
        return em.createNamedQuery("Student.findByLastName", Student.class).setParameter("lastName", lastName).getSingleResult();
    }
    public List<Student> findByNotAttendingToCourse(int courseId) {
        List<Student> a = em.createNamedQuery("Student.findByNotAttendingToCourse", Student.class).setParameter("courseId", courseId).getResultList();
    return a;
    }
}
