package com.marcomc.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.entity.Invitation;

@Mapper(uses = { PersonMapper.class })
public interface InvitationMapper {
	InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

	@Mapping(target = "people", ignore = true)
	InvitationDTO toDTO(Invitation invitation);
	@Mapping(target = "people", ignore = true)
	Invitation toEntity(InvitationDTO invitationDTO);
}
