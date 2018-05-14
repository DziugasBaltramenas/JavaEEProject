package lt.vu.decorators;

import lt.vu.entities.Grade;
import lt.vu.usecases.cdi.dao.GradeDAO;
import lt.vu.usecases.cdi.dao.IGradeDAO;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;

@Decorator
public class GradeTitleDecorator implements IGradeDAO{

    @Inject
    @Delegate
    @Any
    private GradeDAO gradeDAO;

    public void create(Grade grade) {
        int gradeValue = grade.getGrade();
        if(gradeValue < 5){
            grade.setTitle("Labai blogai");
        }else if( gradeValue < 8){
            grade.setTitle("Vidutiniskai");
        }else if( gradeValue < 10){
            grade.setTitle("Gerai");
        }else{
            grade.setTitle("Labai gerai");
        }

        gradeDAO.create(grade);
    }
    public void updateAndFlush(Grade grade) {
       gradeDAO.updateAndFlush(grade);
    }

    public List<Grade> getAllGrades() {
        return gradeDAO.getAllGrades();
    }

    public Grade findById(Integer id) {
        return gradeDAO.findById(id);
    }
}
