package com.marcomc.backend.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "invitations")
public class Invitation {
	@Id
	private String id;
	private String description;
	@Pattern(regexp = "[a-zA-Z0-9]{8,}")
	private String key;
	@OneToMany(mappedBy = "invitation")
	private List<Person> people;

	@PrePersist
	public void generateId() {
		setId(UUID.randomUUID().toString());
	}
}
