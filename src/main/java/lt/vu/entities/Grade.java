/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "GRADE")
@NamedQueries({
        @NamedQuery(name = "Grade.findAll", query = "SELECT s FROM Grade s"),
        @NamedQuery(name = "Grade.findById", query = "SELECT s FROM Grade s WHERE s.id = :id")
})
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = {"id", "grade"})
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Max(10)
    @Min(1)
    @Column(name = "GRADE")
    private Integer grade;

    @Size(min = 3, max = 20)
    @Column(name = "TITLE")
    private String title;

    @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
    @ManyToOne
    @JohnzonIgnore
    private Course course;

    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    @ManyToOne
    @JohnzonIgnore
    private Student student;

    @Version
    @Column(name = "OPT_LOCK_VERSION", nullable = false)
    private int optLockVersion;

}
