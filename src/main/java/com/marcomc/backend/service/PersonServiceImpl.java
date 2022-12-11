package com.marcomc.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.entity.Person;
import com.marcomc.backend.mapper.PersonMapper;
import com.marcomc.backend.respository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public PersonDTO create(PersonDTO personDTO) {
		Person person = PersonMapper.INSTANCE.toEntity(personDTO);
		person = personRepository.save(person);
		return PersonMapper.INSTANCE.toDTO(person);
	}

	@Override
	public List<PersonDTO> getByInvitationId(String id) {
		return personRepository.findByInvitationId(id)
		.stream()
		.map(PersonMapper.INSTANCE::toDTO)
		.toList();
	}
}
