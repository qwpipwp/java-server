package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "attendence",
        uniqueConstraints = {
        })
public class Attendence {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="courseId")
    private Course courseId;
    private String attendence;


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

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

}
