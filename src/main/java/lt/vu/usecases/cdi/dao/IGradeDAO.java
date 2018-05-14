package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Grade;

import java.util.List;

public interface IGradeDAO {
     void create(Grade grade);
     void updateAndFlush(Grade grade);
     List<Grade> getAllGrades();
     Grade findById(Integer id);
}

