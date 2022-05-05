package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Activity;
import org.fatmansoft.teach.models.Honor;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Integer> {

    @Query(value = "select max(id) from Activity  ")
    Integer getMaxId();

    @Query(value = "from Activity where ?1='' or studentId like %?1% or studentName like %?1% ")
    List<Activity> findActivityListByNumName(String numName);

    @Query(value = "select * from activity  where ?1='' or studentId like %?1% or studentName like %?1% ", nativeQuery = true)
    List<Activity> findActivityListByNumNameNative(String numName);


}
