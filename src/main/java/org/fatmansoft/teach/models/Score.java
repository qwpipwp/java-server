package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "score",
        uniqueConstraints = {
        })
public class Score {
    @Id
    private Integer id;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="studentId")
    private Student student;

    @ManyToOne(targetEntity = Course.class)
    @JoinColumn(name="courseId")
    private Course course;

    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
