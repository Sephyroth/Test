package com.test.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long latitude;
	
	private Long longitude;
	
	private String complement;
	
	@Column(unique=true)
	@NotNull(message = "The zipcode cannot be null")
	@NotEmpty(message = "The zipcode cannot be empty")
	private String zipcode;	
	
	@NotNull(message = "The street name cannot be null")
	@NotEmpty(message = "The street name cannot be empty")
	private String streetName;
	
	@NotNull(message = "The street number cannot be null")
	@NotEmpty(message = "The street number cannot be empty")
	private String number;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Neighbourhood neighbourhood;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private City city;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private State state;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Country country;
}
