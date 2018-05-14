package lt.vu.cachepropagation;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Grade;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SynchronizationType;
import java.util.Date;

@Named
@Stateful
@RequestScoped
@Getter
@Setter
public class GradeBean {

    private Grade grade = new Grade();

}