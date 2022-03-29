package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "score",
        uniqueConstraints = {
        })
public class Score {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="courseId")
    private Course courseId;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return studentId;
    }

    public void setStudent(Student studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return courseId;
    }

    public void setCourse(Course courseId) {
        this.courseId = courseId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
