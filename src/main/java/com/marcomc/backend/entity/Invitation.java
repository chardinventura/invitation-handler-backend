package com.marcomc.backend.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	@Column(nullable = false)
	private String password;
	private String description;
	@OneToMany(mappedBy = "invitation")
	private List<Person> people;
}
