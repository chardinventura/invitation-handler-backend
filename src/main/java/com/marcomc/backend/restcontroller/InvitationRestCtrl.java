package com.marcomc.backend.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.service.InvitationService;

@RestController
@RequestMapping("/api/invitations")
@CrossOrigin
public class InvitationRestCtrl {

	@Autowired
	private InvitationService invitationService;

	@GetMapping
	public List<InvitationDTO> getAll() {
		return invitationService.getAll();
	}

	@PostMapping("/{id}/validate")
	public ResponseEntity<Object> validate(@PathVariable String id, @RequestBody InvitationDTO invitationDTO) {
		return invitationService.existsByIdAndKey(id, invitationDTO)
		? ResponseEntity.ok().build()
		: ResponseEntity.badRequest().build();
	}

	@PutMapping("/register-attendance/{invitationId}")
	public ResponseEntity<Object> registerAttendance(@PathVariable String invitationId, @RequestBody InvitationDTO invitationDTO) {
		invitationDTO.setId(invitationId);
		invitationService.registerAttendance(invitationDTO);
		return ResponseEntity.ok().build();
	}
}