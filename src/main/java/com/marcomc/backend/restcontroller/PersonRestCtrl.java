package com.marcomc.backend.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.service.PersonService;

@RestController
@RequestMapping("api/people")
@CrossOrigin
public class PersonRestCtrl {

	@Autowired
	private PersonService personService;

	@GetMapping("/invitation/{id}")
	public List<PersonDTO> getByInvitationId(@PathVariable String id) {
		return personService.getByInvitationId(id);
	}
}