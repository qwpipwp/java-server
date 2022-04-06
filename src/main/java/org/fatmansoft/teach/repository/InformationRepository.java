package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Information;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information,Integer> {
    Optional<Information> findByStudentId(Student studentId);
    Optional<Information> findByStudentName(Student studentName);

    @Query(value = "select max(id) from Information  ")
    Integer getMaxId();

    @Query(value = "from Information where ?1='' or studentId like %?1% or studentName like %?1% ")
    List<Information> findInformationListByNumName(String numName);

    @Query(value = "select * from information  where ?1='' or studentId like %?1% or studentName like %?1% ", nativeQuery = true)
    List<Information> findInformationListByNumNameNative(String numName);
}
