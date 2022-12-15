package com.marcomc.backend.service;

import java.util.List;

import com.marcomc.backend.dto.PersonDTO;

public interface PersonService {
	PersonDTO create(PersonDTO personDTO);
	List<PersonDTO> getByInvitationId(String id);
	void create(List<PersonDTO> people);
}
