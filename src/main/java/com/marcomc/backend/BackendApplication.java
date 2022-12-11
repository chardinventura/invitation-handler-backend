package com.marcomc.backend;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcomc.backend.dto.InvitationDTO;
import com.marcomc.backend.dto.PersonDTO;
import com.marcomc.backend.service.InvitationService;
import com.marcomc.backend.service.PersonService;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	@Autowired
	private InvitationService invitationService;
	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		InvitationDTO invitationDTO = invitationService.create("12345678", "Familia Test");

		List<PersonDTO> people = Arrays.asList(
				PersonDTO.builder()
						.firstName("Martin")
						.lastName("Rodriguez")
						.build(),

				PersonDTO.builder()
						.firstName("Santiago")
						.lastName("Montero")
						.build(),

				PersonDTO.builder()
						.firstName("Marta")
						.lastName("De Gracia")
						.build(),

				PersonDTO.builder()
						.firstName("Juanna")
						.lastName("Melano")
						.build());

		people.forEach((person) -> {
			person.setInvitationId(invitationDTO.getId());
			personService.create(person);
		});
	}
}