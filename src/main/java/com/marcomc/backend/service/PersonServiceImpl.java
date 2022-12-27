package com.marcomc.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcomc.backend.dto.InvitationDTO;
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
	public List<PersonDTO> getByInvitation(InvitationDTO invitationDTO) {
		return personRepository.findByInvitationIdAndInvitationKey(invitationDTO.getId(), invitationDTO.getKey())
				.stream()
				.map(PersonMapper.INSTANCE::toDTO)
				.toList();
	}
	@Override
	public List<PersonDTO> getByInvitationId(String invitationId) {
		return personRepository.findByInvitationId(invitationId)
				.stream()
				.map(PersonMapper.INSTANCE::toDTO)
				.toList();
	}

	@Override
	public void create(List<PersonDTO> people) {
		personRepository.saveAll(people.stream()
				.map(PersonMapper.INSTANCE::toEntity)
				.toList());
	}

	@Override
	public List<PersonDTO> getAll() {
		return personRepository.findAll(sortByInvitationDescriptionAsc())
		.stream()
		.map(PersonMapper.INSTANCE::toDTO)
		.toList();
	}

	@Override
	public Optional<PersonDTO> getById(long id) {
		return personRepository.findById(id)
		.map(PersonMapper.INSTANCE::toDTO);
	}

	@Override
	public void update(long id, PersonDTO personDTO) {
		Person person = PersonMapper.INSTANCE.toEntity(personDTO);
		person.setId(id);
		personRepository.save(person);
	}

	@Override
	public void delete(long id) {
		personRepository.deleteById(id);
	}

	private Sort sortByInvitationDescriptionAsc() {
		return Sort.by(Direction.ASC, "invitation.description");
	}
}
