package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PracticeRepository extends JpaRepository<Practice,Integer> {

    @Query(value = "select max(id) from Practice  ")
    Integer getMaxId();

    @Query(value = "from Practice where ?1='' or studentId like %?1% or studentName like %?1% ")
    List<Practice> findPracticeListByNumName(String numName);

    @Query(value = "select * from practice  where ?1='' or studentId like %?1% or studentName like %?1% ", nativeQuery = true)
    List<Practice> findPracticeListByNumNameNative(String numName);

}
