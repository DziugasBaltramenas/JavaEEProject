package lt.vu.cachepropagation;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Course;
import lt.vu.usecases.cdi.dao.CourseDAO;

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
public class CourseBeans {

    @Inject
    CourseDAO courseDAO;

    @Getter
    private Map<String, Integer> courses = new HashMap<String, Integer>();
    @Getter
    @Setter
    private Integer course;

    @PostConstruct
    public void init() {
        for (Course course : courseDAO.getAllCourses()) {
            courses.put(course.getName(), course.getId());
        }
    }


}