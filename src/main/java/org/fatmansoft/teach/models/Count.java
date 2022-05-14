package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "count",
        uniqueConstraints = {
        })
public class Count {
    @Id
    private Integer id;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="studentId")
    private Student student;//与学生进行多对一联系


    private Integer courseCount;
    private Integer honorCount;
    private Integer activityCount;
    private Integer practiceCount;

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

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer course) {
        this.courseCount = course;
    }

    public Integer getHonorCount() {
        return honorCount;
    }

    public void setHonorCount(Integer honorCount) {
        this.honorCount = honorCount;
    }
    public Integer getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(Integer activityCount) {
        this.activityCount = activityCount;
    }
    public Integer getPracticeCount() {
        return practiceCount;
    }

    public void setPracticeCount(Integer practiceCount) {
        this.practiceCount = practiceCount;
    }
}
