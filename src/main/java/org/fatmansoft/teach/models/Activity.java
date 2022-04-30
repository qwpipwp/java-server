package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import org.fatmansoft.teach.models.Student;
@Entity
@Table(	name = "activity",
        uniqueConstraints = {
        })
public class Activity {
    @Id
    private Integer id;
    @NotBlank
    @Size(max = 20)
    private String activityNum;
    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="studentName")
    private Student studentName;

    @Size(max = 50)
    private String activityName;
    private Date dates ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId_activity() {
        return studentId;
    }

    public void setStudentId_activity(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentName_activity() {
        return studentName;
    }

    public void setStudentName_activity(Student studentName) {
        this.studentName = studentName;
    }


    public String getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(String activityNum) {
        this.activityNum = activityNum;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName =activityName;
    }
    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }


}
