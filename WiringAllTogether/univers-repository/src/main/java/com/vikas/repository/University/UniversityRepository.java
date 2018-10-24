package com.vikas.repository.University;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vikas.model.University.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

	@Query("select u from University u order by u.UniversityName")
	public List<University> getAllUniversityName();
	
	@Query("select count(s) from student s where s.university.ID =:universityId")
	public Integer getNumOfStudentForUniversity(@Param("universityId") Integer universityId);

}
