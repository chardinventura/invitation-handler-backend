package com.marcomc.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDTO {
	private long id;
	@Pattern(regexp = "[a-zA-Z0-9\s]{1,}")
	private String firstName;
	@Pattern(regexp = "[a-zA-Z0-9\s]{1,}")
	private String lastName;
	private boolean attendance;
	@NotBlank
	private String invitationId;
}
