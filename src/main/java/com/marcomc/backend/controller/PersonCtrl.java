package com.marcomc.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.service.InvitationService;
import com.marcomc.backend.service.PersonService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/people" )
public class PersonCtrl {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private PersonService personService;
	@Autowired
	private InvitationService invitationService;

	enum Action {
		UPDATE,
		REGISTER
	}

	@GetMapping
	public String index(Model model) {
		System.out.println("urcaoehuaohureu");
		model.addAttribute("people", personService.getAll());
		return "person/index";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute("person") PersonDTO personDTO, Model model, BindingResult bindingResult) {
		final String header = messageSource.getMessage("header.register", null, null);
		model.addAttribute("header", header);
		model.addAttribute("action", Action.REGISTER);
		model.addAttribute("invitations", invitationService.getAll());
		return "person/person";
	}

	@PostMapping("/create")
	public String onCreate(@Valid PersonDTO personDTO, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			final String header = messageSource.getMessage("header.register", null, null);
			model.addAttribute("header", header);
			model.addAttribute("action", Action.REGISTER);
			model.addAttribute("invitations", invitationService.getAll());
			return "person/person";
		}
		personService.create(personDTO);
		return "redirect:/people";
	}
}
