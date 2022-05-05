package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Homework;
import org.fatmansoft.teach.models.Student;
import org.fatmansoft.teach.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface HomeworkRepository extends JpaRepository<Homework,Integer> {
    @Query(value = "select max(id) from Homework  ")
    Integer getMaxId();

    @Query(value = "from Homework where ?1='' or studentId like %?1% or courseId like %?1% ")
    List<Homework> findHomeworkListByNumName(String numName);

    @Query(value = "select * from homework  where ?1='' or studentId like %?1% or courseId like %?1% ", nativeQuery = true)
    List<Homework> findHomeworkListByNumNameNative(String numName);
}
