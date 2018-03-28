package lt.vu.cachepropagation;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Course;
import lt.vu.entities.Student;
import lt.vu.usecases.cdi.dao.CourseDAO;
import lt.vu.usecases.cdi.dao.StudentDAO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
@RequestScoped // @SessionScoped
public class StudentBeans {

    @Inject
    StudentDAO studentDAO;

    @Getter
    private Map<String, Integer> students = new HashMap<String, Integer>();
    @Getter
    @Setter
    private Integer id;

    @PostConstruct
    public void init() {
        for (Student student : studentDAO.getAllStudents()) {
            students.put(student.getFirstName() + " " + student.getLastName(), student.getId());
        }
    }


}