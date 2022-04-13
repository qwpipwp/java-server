package org.fatmansoft.teach.repository;

import org.fatmansoft.teach.models.FamilyMember;
import org.fatmansoft.teach.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember,Integer> {
    Optional<FamilyMember> findByStudentId(Student studentId);

    @Query(value = "select max(id) from FamilyMember  ")
    Integer getMaxId();

    @Query(value = "from FamilyMember where ?1='' or studentId like %?1% or studentName like %?1% ")
    List<FamilyMember> findFamilyMemberListByNumName(String numName);

    @Query(value = "select * from familyMember  where ?1='' or studentId like %?1% or studentName like %?1% ", nativeQuery = true)
    List<FamilyMember> findFamilyMemberListByNumNameNative(String numName);
}
