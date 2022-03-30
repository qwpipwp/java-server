package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.Honor;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HonorRepository extends JpaRepository<Honor,Integer> {
    Optional<Honor> findByStudentId(Student studentNum);
    Optional<Honor> findByStudentName(Student studentName);

    @Query(value = "select max(id) from Honor  ")
    Integer getMaxId();

    @Query(value = "from Honor where ?1='' or studentId like %?1% or studentName like %?1% ")
    List<Honor> findHonorListByNumName(String numName);

    @Query(value = "select * from honor  where ?1='' or studentId like %?1% or studentName like %?1% ", nativeQuery = true)
    List<Honor> findHonorListByNumNameNative(String numName);
}
