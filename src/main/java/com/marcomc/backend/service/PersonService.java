package com.marcomc.backend.service;

import java.util.List;
import java.util.Optional;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.dto.PersonDTO;

public interface PersonService {
	PersonDTO create(PersonDTO personDTO);
	List<PersonDTO> getByInvitation(InvitationDTO invitationDTO);
	List<PersonDTO> getByInvitationId(String invitationId);
	void create(List<PersonDTO> people);
	List<PersonDTO> getAll();
	Optional<PersonDTO> getById(long id);
	void delete(long id);
	void update(long id, PersonDTO personDTO);
}
