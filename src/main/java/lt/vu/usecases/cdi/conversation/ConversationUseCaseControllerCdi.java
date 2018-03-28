package lt.vu.usecases.cdi.conversation;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Course;
import lt.vu.entities.Grade;
import lt.vu.entities.Student;
import lt.vu.usecases.cdi.dao.CourseDAO;
import lt.vu.usecases.cdi.dao.GradeDAO;
import lt.vu.usecases.cdi.dao.StudentDAO;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ConversationScoped
@Slf4j
public class ConversationUseCaseControllerCdi implements Serializable {

    private static final String PAGE_INDEX_REDIRECT = "index?faces-redirect=true";

    private enum CURRENT_FORM {
        CREATE_COURSE, CREATE_STUDENT, CONFIRMATION, GET_COURSE, GET_STUDENT,  CREATE_GRADE
    }

    @Setter
    @Getter
    private String studentID;

    @Inject
    private EntityManager em;

    @Inject
    @Getter
    private Conversation conversation;

    @Inject
    private CourseDAO courseDAO;
    @Inject
    private StudentDAO studentDAO;
    @Inject
    private GradeDAO gradeDAO;

    @Getter
    private Course course = new Course();
    @Getter
    private Student student = new Student();
    @Getter
    private Grade grade = new Grade();

    @Getter
    private List<Student> allStudents;

    private CURRENT_FORM currentForm = CURRENT_FORM.GET_COURSE;
    public boolean isCurrentForm(CURRENT_FORM form) {
        return currentForm == form;
    }

    @PostConstruct
    public void init() {
        loadAllStudents();
    }

    /**
     * The first conversation step.
     */
    public void createCourse() {
        conversation.begin();
        currentForm = CURRENT_FORM.CREATE_STUDENT;
    }

    /**
     * The second conversation step.
     */
    public void createStudent() {
        student.getCourseList().add(course);
        course.getStudentList().add(student);
        currentForm = CURRENT_FORM.CONFIRMATION;
    }

    /**
     * The last conversation step.
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public String ok() {
        try {
            courseDAO.create(course);
            studentDAO.create(student);
            em.flush();
            Messages.addGlobalInfo("Success!");
        } catch (OptimisticLockException ole) {
            // Other user was faster...
            Messages.addGlobalWarn("Please try again");
            log.warn("Optimistic Lock violated: ", ole);
        } catch (PersistenceException pe) {
            // Some problems with DB - most often this is programmer's fault.
            Messages.addGlobalError("Finita la commedia...");
            log.error("Error ending conversation: ", pe);
        }
        Faces.getFlash().setKeepMessages(true);
        conversation.end();
        return PAGE_INDEX_REDIRECT;
    }

    /**
     * The last (alternative) conversation step.
     */
    public String cancel() {
        conversation.end();
        return PAGE_INDEX_REDIRECT;
    }

    private void loadAllStudents() {
        allStudents = studentDAO.getAllStudents();
    }

    public void findStudent(int id){
        student = studentDAO.findById(id);
        currentForm = CURRENT_FORM.CREATE_GRADE;

    }


    public void findCourse(int id){
        conversation.begin();
        course = courseDAO.findById(id);
        currentForm = CURRENT_FORM.GET_STUDENT;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public String createGrade(){
        grade.setCourse(course);
        grade.setStudent(student);
        gradeDAO.create(grade);
        em.flush();

        conversation.end();
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

}
