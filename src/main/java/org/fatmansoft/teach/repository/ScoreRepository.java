package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Course;
import org.fatmansoft.teach.models.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query(value = "select max(id) from Score")
    Integer getMaxId();

    @Query(value = "from Score where ?1='' or student.studentName = ?1 or student.studentNum = ?1 ")
    List<Score> findScoreListByStudentNumName(String numName);

    @Query(value = "from Score where ?1='' or course.courseName = ?1 or course.courseNum = ?1 ")
    List<Score> findScoreListByCourseNumName(String numName);

    @Query(value = "from Score where ?1='' or student.studentName like %?1% or course.courseName like %?1% or student.studentNum like %?1% or course.courseNum like %?1%")
    List<Score> findScoreListByNumName(String numName);

    @Query(value = "select * from Score where ?1 = '' or student_studentName like %?1% or course_courseName like %?1% ", nativeQuery = true)
    List<Score> findScoreListByNumNameNative(String numName);

    @Query(value = "select s.course from Score s where s.student.id =?1")
    List<Course> findCourseList(Integer studentId);

    @Query(value = "from Score where ?1='' or student.id = ?1 ")
    List<Score> findScoreListByStudentId(Integer studentId);
}
