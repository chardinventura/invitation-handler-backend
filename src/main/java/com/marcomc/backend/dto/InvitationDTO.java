package com.marcomc.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvitationDTO {

	public InvitationDTO(String id) {
		this.id = id;
	}

	private String id;
	private String password;
	private String description;
	private List<PersonDTO> people;
}
