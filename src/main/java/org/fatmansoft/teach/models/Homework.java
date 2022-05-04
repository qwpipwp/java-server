package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "homework",
        uniqueConstraints = {
        })
public class Homework {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="courseId")
    private Course courseId;
    private String homework;
    private String homeworkIsDone;
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

    public String getHomework() {
        return homework;
    }

    public void setHomework(String  homework) {
        this.homework = homework;
    }

    public String getHomeworkIsDone() {
        return homeworkIsDone;
    }

    public void setHomeworkIsDone(String  homeworkIsDone) {
        this.homeworkIsDone = homeworkIsDone;
    }
}
