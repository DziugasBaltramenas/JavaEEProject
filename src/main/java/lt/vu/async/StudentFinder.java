package lt.vu.async;

import lt.vu.entities.Student;
import lt.vu.usecases.cdi.dao.StudentDAO;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.Future;

@ApplicationScoped
public class StudentFinder implements Serializable {
    @Inject
    private StudentDAO studentDAO;

    @Futureable
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Future<String> findStudent(String lastName) {
        String txt = lastName + "'";
        System.out.println("Started registration of " + txt);
        try {
            Thread.sleep(3000); // sleep for 3 seconds
        } catch (InterruptedException e) {
        }
        Student student = studentDAO.findByLastName(lastName);
        if (student != null) {
            txt =  lastName +"!";
        } else {
            txt = "Student "+lastName +"not found :((";
        }
        System.out.println("Student found: " + txt);
        return new AsyncResult<>(txt);
    }
}
