package com.marcomc.backend.dto;

import jakarta.validation.constraints.NotBlank;
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
	@NotBlank(message = "{validation.firstName}")
	private String firstName;
	@NotBlank(message = "{validation.lastName}")
	private String lastName;
	private boolean attendance;
	@NotBlank(message = "{validation.notNull}")
	private String invitationId;
}
