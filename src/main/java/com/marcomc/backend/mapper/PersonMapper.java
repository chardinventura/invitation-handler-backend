package com.marcomc.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.entity.Person;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(source = "invitationId", target = "invitation.id")
	Person toEntity(PersonDTO personDTO);
	@Mapping(source = "invitation.id", target = "invitationId")
	PersonDTO toDTO(Person person);
}
