package com.marcomc.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.entity.Invitation;

@Mapper
public interface InvitationMapper {
	InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);

	InvitationDTO toDTO(Invitation invitation);
}
