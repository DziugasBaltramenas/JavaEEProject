package lt.vu.usecases.cdi.dao;

import lt.vu.entities.Grade;

import javax.enterprise.inject.Alternative;

@Alternative
public class GradeDAOAdvanced extends GradeDAO {

    public Grade findById(Integer id) {
        System.out.println("ALTERNATIVE FOR GRADE");
        return em.find(Grade.class, id);
    }

    public void create(Grade grade) {
        System.out.println("ALTERNATIVE FOR GRADE");
        em.persist(grade);
    }
}
