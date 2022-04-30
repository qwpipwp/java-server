package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "familyMember",
        uniqueConstraints = {
        })
public class FamilyMember {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student studentId;
    @ManyToOne
    @JoinColumn(name = "studentName")
    private Student studentName;

    private String name;
    private String sex;
    private String rel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId_fa() {
        return studentId;
    }

    public void setStudentId_fa(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentName_fa() {
        return studentName;
    }

    public void setStudentName_fa(Student studentName) {
        this.studentName = studentName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
}
