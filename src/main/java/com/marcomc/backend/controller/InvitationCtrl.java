package com.marcomc.backend.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.service.InvitationService;

@Controller
@RequestMapping("/invitations")
public class InvitationCtrl {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private InvitationService invitationService;

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
	public String create(Model model) {
		final String header = messageSource.getMessage("edit", null, null);
		model.addAttribute("header", header);
		model.addAttribute("action", Action.REGISTER);
		model.addAttribute("invitation", InvitationDTO.builder()
				.id(UUID.randomUUID().toString())
				.build());
		return "invitation/invitation";
	}

	@PostMapping("/create")
	public String onCreate(InvitationDTO invitation, Model model) {
		invitationService.create(invitation.getDescription());
		return "redirect:/invitations";
	}

	@GetMapping("/details/{id}")
	public String details(@PathVariable String id, Model model) {
		model.addAttribute("invitation", invitationService.findById(id));
		return "invitation/details";
	}
}
