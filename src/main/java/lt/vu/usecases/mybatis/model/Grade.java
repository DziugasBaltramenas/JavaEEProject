package lt.vu.usecases.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Grade {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.GRADE.ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.GRADE.GRADE
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    private Integer grade;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.GRADE.STUDENT_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    private Integer studentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.GRADE.COURSE_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    private Integer courseId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.GRADE.ID
     *
     * @return the value of PUBLIC.GRADE.ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.GRADE.ID
     *
     * @param id the value for PUBLIC.GRADE.ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.GRADE.GRADE
     *
     * @return the value of PUBLIC.GRADE.GRADE
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.GRADE.GRADE
     *
     * @param grade the value for PUBLIC.GRADE.GRADE
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.GRADE.STUDENT_ID
     *
     * @return the value of PUBLIC.GRADE.STUDENT_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.GRADE.STUDENT_ID
     *
     * @param studentId the value for PUBLIC.GRADE.STUDENT_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.GRADE.COURSE_ID
     *
     * @return the value of PUBLIC.GRADE.COURSE_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.GRADE.COURSE_ID
     *
     * @param courseId the value for PUBLIC.GRADE.COURSE_ID
     *
     * @mbg.generated Wed Mar 28 12:32:43 EEST 2018
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


   /* @Getter
    @Setter
    private Student student;

    @Getter
    @Setter
    private Course course;*/



}