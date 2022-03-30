package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "honor",
        uniqueConstraints = {
        })
public class Honor {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="studentName")
    private Student studentName;
    private String honor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId_honor() {
        return studentId;
    }

    public void setStudentId_honor(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentName_honor() {
        return studentName;
    }

    public void setStudentName_honor(Student studentName) {
        this.studentName = studentName;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }
}
