package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(	name = "practice",
        uniqueConstraints = { })
public class Practice {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "studentName")
    private Student studentName;

    @NotBlank
    @Size(max = 20)
    private String practiceNum;

    @Size(max = 50)
    private String practiceName;
    private Date practiceDate;
    private String practiceKind;

    public Student getStudentId_practice() {
        return studentId;
    }

    public void setStudentId_practice(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentName_practice() {
        return studentName;
    }

    public void setStudentName_practice(Student studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPracticeNum() {
        return practiceNum;
    }

    public void setPracticeNum(String practiceNum) {
        this.practiceNum = practiceNum;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public Date getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(Date practiceDate) {
        this.practiceDate = practiceDate;
    }

    public String getPracticeKind() {
        return practiceKind;
    }

    public void setPracticeKind(String practiceKind) {
        this.practiceKind = practiceKind;
    }

}
