package com.marcomc.backend.dto;

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
	private String firstName;
	private String lastName;
	private boolean attendance;
	private String invitationId;
}
