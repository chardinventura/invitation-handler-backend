package com.marcomc.backend.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.service.PersonService;

@RestController
@RequestMapping("api/people")
@CrossOrigin
public class PersonRestCtrl {

	@Autowired
	private PersonService personService;

	@PostMapping("/invitation")
	public List<PersonDTO> getByInvitation(@RequestBody InvitationDTO invitationDTO) {
		return personService.getByInvitation(invitationDTO);
	}
}