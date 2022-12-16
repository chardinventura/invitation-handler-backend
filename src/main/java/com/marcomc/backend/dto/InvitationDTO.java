package com.marcomc.backend.dto;

import java.util.List;

import jakarta.validation.constraints.Pattern;
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
	private String description;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "{validation.key}")
	private String key;
	private List<PersonDTO> people;
}
