package com.marcomc.backend.service;

import java.util.List;

import com.marcomc.backend.dto.InvitationDTO;

public interface InvitationService {
	InvitationDTO create(InvitationDTO invitation);
	InvitationDTO findById(String id);
	boolean existsByIdAndKey(String id, InvitationDTO invitationDTO) ;
	void registerAttendance(InvitationDTO invitationDTO);
	List<InvitationDTO> getAll();
	void delete(String id);
	void update(String id, InvitationDTO invitation);
}
