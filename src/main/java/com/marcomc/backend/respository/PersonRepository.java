package com.marcomc.backend.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcomc.backend.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>  {
	List<Person> findByInvitationId(String id);
}
