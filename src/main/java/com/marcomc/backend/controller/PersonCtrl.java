package com.marcomc.backend.controller;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.service.InvitationService;
import com.marcomc.backend.service.PersonService;

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
		model.addAttribute("people", personService.getAll());
		model.addAttribute("invitations", getInvitations());
		return "person/index";
	}

	@GetMapping("/{id}")
	public String details(@PathVariable long id, Model model) {
		final PersonDTO personDTO = personService.getById(id).get();
		final String invitationId = personDTO.getInvitationId();
		model.addAttribute("person", personDTO);
		model.addAttribute("invitation", invitationService.findById(invitationId));
		return "person/details";
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
	public String onCreate(@Validated @ModelAttribute("person") PersonDTO personDTO, BindingResult bindingResult, Model model) {
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

	@GetMapping("/update/{id}")
	public String update(@PathVariable long id, Model model) {
		final String header = messageSource.getMessage("header.edit", null, null);
		model.addAttribute("header", header);
		model.addAttribute("action", Action.UPDATE);
		model.addAttribute("person", personService.getById(id).get());
		model.addAttribute("invitations", invitationService.getAll());
		return "person/person";
	}

	@PostMapping("/update/{id}")
	public String onUpdate(@PathVariable long id, @Validated @ModelAttribute("person") PersonDTO personDTO, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			final String header = messageSource.getMessage("header.edit", null, null);
			model.addAttribute("header", header);
			model.addAttribute("action", Action.UPDATE);
			model.addAttribute("invitations", invitationService.getAll());
			return "person/person";
		}
		personService.update(id, personDTO);
		return "redirect:/people";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model) {
		final PersonDTO personDTO = personService.getById(id).get();
		final String invitationId = personDTO.getInvitationId();

		model.addAttribute("person", personDTO);
		model.addAttribute("invitation", invitationService.findById(invitationId));
		return "person/delete";
	}

	@PostMapping("/delete/{id}")
	public String onDelete(@PathVariable long id) {
		personService.delete(id);
		return "redirect:/people";
	}

	Map<String, InvitationDTO> getInvitations() {
		return invitationService.getAll()
		.stream()
		.collect(Collectors.toMap(InvitationDTO::getId, Function.identity()));
	}
}
