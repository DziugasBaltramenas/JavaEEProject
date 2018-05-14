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
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class RadioButtonsBeans implements Serializable {

    @Inject
    CourseDAO courseDAO;

    @Inject
    StudentDAO studentDAO;

    @Getter
    @Setter
    private Integer course;
    @Getter
    @Setter
    private Integer student;

    public Map<String, Integer> getCourseStudents(){
        Map<String, Integer> students = new HashMap<String, Integer>();
        Course course = courseDAO.findById(this.course);
        for (Student student : course.getStudentList()) {
            students.put(student.getFirstName() + " " + student.getLastName(), student.getId());
        }

        return students;
    }

    public Map<String, Integer> getNoCourseStudents(){
        Map<String, Integer> students = new HashMap<String, Integer>();
        Course course = courseDAO.findById(this.course);
        for (Student student : studentDAO.findByNotAttendingToCourse(course.getId())) {
            students.put(student.getFirstName() + " " + student.getLastName(), student.getId());
        }

        return students;
    }

    public Map<String, Integer> getCourses(){
        Map<String, Integer> courses = new HashMap<String, Integer>();
        for (Course course : courseDAO.getAllCourses()) {
            courses.put(course.getName(), course.getId());
        }

        return courses;
    }

}