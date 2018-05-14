package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Student;

import javax.enterprise.inject.Specializes;
import java.util.List;

@Specializes
public class StudentDAOAdvanced extends StudentDAO {
    @Override
    public List<Student> findByNotAttendingToCourse(int courseId)
    {
        System.out.println("SPECIALIZES FOR STUDENT");
        List<Student> students = em.createNamedQuery("Student.findByNotAttendingToCourse", Student.class).setParameter("courseId", courseId).getResultList();
        return students;
    }
}
