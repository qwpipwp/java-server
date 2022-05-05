package org.fatmansoft.teach.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(	name = "student",
        uniqueConstraints = {
        })
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String studentNum;

    @Size(max = 50)
    private String studentName;
    @Size(max = 2)
    private String sex;
    private Integer age;
    private Date birthday;
    @NotBlank
    @Size(max = 20)
    private String tel;
    @Size(max = 50)
    private String address;
    @NotBlank
    private String grade;//年级信息
    private String linkMan_1;
    private String tel_1;
    private String relation_1;


    @ManyToMany
    @JoinTable(
            name = "selections",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> courses;



    public void addCourse(Course course){
        this.courses.add(course);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public String getLinkMan_1() {
        return linkMan_1;
    }

    public void setLinkMan_1(String linkMan_1) {
        this.linkMan_1 = linkMan_1;
    }

    public String getTel_1() {
        return tel_1;
    }

    public void setTel_1(String tel_1) {
        this.tel_1 = tel_1;
    }

    public String getRelation_1() {
        return relation_1;
    }

    public void setRelation_1(String relation_1) {
        this.relation_1 = relation_1;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
