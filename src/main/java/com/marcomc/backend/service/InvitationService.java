package com.marcomc.backend.service;

import java.util.List;

import com.marcomc.backend.dto.InvitationDTO;

public interface InvitationService {
	InvitationDTO create(String description);
	InvitationDTO findById(String id);
	boolean isValid(InvitationDTO invitationDTO) ;
	void registerAttendance(InvitationDTO invitationDTO);
	List<InvitationDTO> getAll();
}
