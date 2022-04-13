package org.fatmansoft.teach.models;

import javax.persistence.*;

@Entity
@Table(	name = "information",
        uniqueConstraints = {
        })
public class Information {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name ="studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name ="studentName")
    private Student studentName;

    private Integer telephoneNumber;

    private String preEnrolmentInformation;

    private String social;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId_information() {
        return studentId;
    }

    public void setStudentId_information(Student studentId) {
        this.studentId = studentId;
    }

    public Student getStudentName_information() {
        return studentName;
    }

    public void setStudentName_information(Student StudentName) {
        this.studentName = StudentName;
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getPreEnrolmentInformation() {
        return preEnrolmentInformation;
    }

    public void setPreEnrolmentInformation(String preEnrolmentInformation) {
        this.preEnrolmentInformation = preEnrolmentInformation;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }
}
