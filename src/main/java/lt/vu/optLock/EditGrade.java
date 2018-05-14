package lt.vu.optLock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.Grade;
import lt.vu.interceptors.MyInterceptor;
import lt.vu.usecases.cdi.dao.GradeDAO;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
@Slf4j
public class EditGrade implements Serializable {

    @Getter private Grade selectedGrade;
    @Getter private Grade conflictingGrade;

    @Getter private List<Grade> allGrades;

    @Inject private GradeDAO gradeDAO;


    @PostConstruct
    public void init() {
        reloadAll();
    }

    @MyInterceptor
    public Grade prepareForEditing(Grade grade) {
        selectedGrade = grade;
        allGrades = gradeDAO.getAllGrades();
        conflictingGrade = null;
        return selectedGrade;
    }


    @Transactional
    public void updateSelectedGrade() {
        try {
            gradeDAO.updateAndFlush(selectedGrade);
            reloadAll();
        } catch (OptimisticLockException ole) {
            conflictingGrade = gradeDAO.findById(selectedGrade.getId());
            System.out.println(conflictingGrade);
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    @Transactional
    public void overwriteGrade() {
        System.out.println("ivyko call");
        selectedGrade.setOptLockVersion(conflictingGrade.getOptLockVersion());
        updateSelectedGrade();
    }


    public void reloadAll() {
        allGrades = gradeDAO.getAllGrades();
    }

}