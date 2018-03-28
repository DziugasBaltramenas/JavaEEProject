package lt.vu.usecases.cdi.simple;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.usecases.mybatis.dao.CourseMapper;
import lt.vu.usecases.mybatis.dao.StudentCourseMapper;
import lt.vu.usecases.mybatis.dao.StudentMapper;
import lt.vu.usecases.mybatis.model.Course;
import lt.vu.usecases.mybatis.model.Student;
import lt.vu.usecases.mybatis.model.StudentCourse;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
@Slf4j
public class RequestUseCaseControllerMyBatis implements Serializable {

    private static final String PAGE_INDEX_REDIRECT = "simple-usecase-mybatis?faces-redirect=true";

    private enum CURRENT_FORM {
         GET_COURSE, GET_STUDENT
    }

    private CURRENT_FORM currentForm = CURRENT_FORM.GET_COURSE;
    public boolean isCurrentForm(CURRENT_FORM form) {
        return currentForm == form;
    }


    @Inject
    @Getter
    private Conversation conversation;


    @Getter
    private Course course = new Course();
    @Getter
    private Student student = new Student();
    @Getter
    private List<Student> allStudents;

    @PostConstruct
    public void init() {
        loadAllStudents();
    }

    @Inject
    private StudentMapper studentMapper;
    @Inject
    private CourseMapper courseMapper;
    @Inject
    private StudentCourseMapper studentCourseMapper;


    @Transactional(Transactional.TxType.REQUIRED)
    public String findStudent(int id){
        student = studentMapper.selectByPrimaryKey(id);
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(course.getId());
        studentCourse.setStudentId(student.getId());
        studentCourseMapper.insert(studentCourse);
        conversation.end();
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }


    public void findCourse(int id){
        conversation.begin();
        course = courseMapper.selectByPrimaryKey(id);;
        currentForm = CURRENT_FORM.GET_STUDENT;
    }

    @Transactional
    public void createCourseStudent() {

        studentMapper.insert(student);
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourseId(course.getId());
        studentCourse.setStudentId(student.getId());
        studentCourseMapper.insert(studentCourse);
        log.info("Maybe OK...");
    }

    private void loadAllStudents() {
        allStudents = studentMapper.selectAll();
    }
}
