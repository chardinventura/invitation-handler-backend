package com.marcomc.backend.controller;

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
import com.marcomc.backend.service.InvitationService;
import com.marcomc.backend.service.PersonService;

@Controller
@RequestMapping({ "/", "/invitations" })
public class InvitationCtrl {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private InvitationService invitationService;
	@Autowired
	private PersonService personService;

	enum Action {
		UPDATE,
		REGISTER
	}

	@GetMapping
	public String index(Model model) {
		model.addAttribute("invitations", invitationService.getAll());
		return "invitation/index";
	}

	@GetMapping("/create")
	public String create(@ModelAttribute("invitation") InvitationDTO invitationDTO, Model model) {
		final String header = messageSource.getMessage("header.register", null, null);
		model.addAttribute("header", header);
		model.addAttribute("action", Action.REGISTER);
		return "invitation/invitation";
	}

	@PostMapping("/create")
	public String onCreate(@Validated @ModelAttribute("invitation") InvitationDTO invitation, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			final String header = messageSource.getMessage("header.register", null, null);
			model.addAttribute("header", header);
			model.addAttribute("action", Action.REGISTER);
			return "invitation/invitation";
		}
		invitationService.create(invitation);
		return "redirect:/invitations";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable String id, Model model) {
		final String header = messageSource.getMessage("header.edit", null, null);
		model.addAttribute("header", header);
		model.addAttribute("action", Action.UPDATE);
		model.addAttribute("invitation", invitationService.findById(id));
		return "invitation/invitation";
	}

	@PostMapping("/update/{id}")
	public String onUpdate(@PathVariable String id, @Validated @ModelAttribute("invitation") InvitationDTO invitation, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			final String header = messageSource.getMessage("header.edit", null, null);
			model.addAttribute("header", header);
			model.addAttribute("action", Action.UPDATE);
			return "invitation/invitation";
		}
		invitationService.update(id, invitation);
		return "redirect:/invitations";
	}

	@GetMapping("/{id}")
	public String details(@PathVariable String id, Model model) {
		model.addAttribute("invitation", invitationService.findById(id));
		model.addAttribute("people", personService.getByInvitationId(id));
		return "invitation/details";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id, Model model) {
		model.addAttribute("invitation", invitationService.findById(id));
		model.addAttribute("people", personService.getByInvitationId(id));
		return "invitation/delete";
	}

	@PostMapping("/delete/{id}")
	public String onDelete(@PathVariable String id, Model model) {
		model.addAttribute("invitation", invitationService.findById(id));
		invitationService.delete(id);
		return "redirect:/invitations";
	}
}
