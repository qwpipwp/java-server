package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Score;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score,Integer> {
    Optional<Score> findByStudentId(Student student);
    Optional<Score> findByCourseId(Course course);

    @Query(value = "select max(id) from Score  ")
    Integer getMaxId();

    @Query(value = "from Score where ?1='' or studentId like %?1% or courseId like %?1% ")
    List<Score> findScoreListByNumName(String numName);

    @Query(value = "select * from score  where ?1='' or studentId like %?1% or courseId like %?1% ", nativeQuery = true)
    List<Score> findScoreListByNumNameNative(String numName);
}
