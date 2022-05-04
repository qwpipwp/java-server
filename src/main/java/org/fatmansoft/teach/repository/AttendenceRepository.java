package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Attendence;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {
    Optional<Attendence> findByStudentId(Student student);
    Optional<Attendence> findByCourseId(Course course);

    @Query(value = "select max(id) from Attendence  ")
    Integer getMaxId();

    @Query(value = "from Attendence where ?1='' or studentId like %?1% or courseId like %?1% ")
    List<Attendence> findAttendenceListByNumName(String numName);

    @Query(value = "select * from attendence where ?1='' or studentId like %?1% or courseId like %?1% ", nativeQuery = true)
    List<Attendence> findAttendenceListByNumNameNative(String numName);
}
