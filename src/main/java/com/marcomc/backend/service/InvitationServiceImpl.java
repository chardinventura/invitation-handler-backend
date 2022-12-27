package com.marcomc.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.entity.Invitation;
import com.marcomc.backend.entity.Person;
import com.marcomc.backend.mapper.InvitationMapper;
import com.marcomc.backend.respository.InvitationRepository;
import com.marcomc.backend.respository.PersonRepository;

@Service
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public InvitationDTO findById(String id) {
		Invitation invitation = invitationRepository.findById(id)
		.orElse(new Invitation());
		return InvitationMapper.INSTANCE.toDTO(invitation);
	}

	@Override
	public InvitationDTO create(InvitationDTO invitationDTO) {
		Invitation invitation = InvitationMapper.INSTANCE.toEntity(invitationDTO);

		invitation = invitationRepository.save(invitation);
		return InvitationMapper.INSTANCE.toDTO(invitation);
	}

	@Override
	public boolean existsByIdAndKey(String id, InvitationDTO invitationDTO) {
		return invitationRepository.existsByIdAndKey(id, invitationDTO.getKey());
	}

	@Override
	public void registerAttendance(InvitationDTO invitationDTO) {
		Optional<Invitation> invitation = invitationRepository.findById(invitationDTO.getId());
		if (invitation.isEmpty()) {
			return;
		}
		final Map<Long, Person> people = invitation.get().getPeople()
				.stream()
				.collect(Collectors.toMap(Person::getId, Function.identity()));

		final Map<Long, PersonDTO> peopleDto = invitationDTO.getPeople()
				.stream()
				.collect(Collectors.toMap(PersonDTO::getId, Function.identity()));

		if (people.keySet().containsAll(peopleDto.keySet())) {
			peopleDto.values()
					.stream()
					.map((personDto) -> {
						Person person = people.get(personDto.getId());
						person.setAttendance(personDto.isAttendance());
						return person;
					})
					.forEach(personRepository::save);
		}
	}

	@Override
	public List<InvitationDTO> getAll() {
		return invitationRepository.findAll(sortByDescriptionAsc())
				.stream()
				.map(InvitationMapper.INSTANCE::toDTO)
				.toList();
	}

	@Override
	public void delete(String id) {
		invitationRepository.deleteById(id);
	}

	@Override
	public void update(String id, InvitationDTO invitationDTO) {
		Invitation invitation = InvitationMapper.INSTANCE.toEntity(invitationDTO);
		invitation.setId(id);

		invitationRepository.save(invitation);
	}

	private Sort sortByDescriptionAsc() {
		return Sort.by(Direction.ASC, "description");
	}
}
