package com.test.controller;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.test.domain.Address;
import com.test.domain.City;
import com.test.domain.Country;
import com.test.domain.Neighbourhood;
import com.test.domain.State;
import com.test.service.AddressService;

@ExtendWith(SpringExtension.class)
public class AddressControllerTest {
	
	@InjectMocks
	private AddressController addressController;
	
	@Mock
	private AddressService addressServiceMock;
	
	@BeforeEach
	void setUp() {
		List<Address> address = Arrays.asList(createAddressToBeSaved());
		BDDMockito.when(addressServiceMock.listAll()).thenReturn(address);
		
		BDDMockito.when(addressServiceMock.findByIdOrThrowBadRequestException(1l)).thenReturn(createValidAddress());
		BDDMockito.when(addressServiceMock.save(ArgumentMatchers.any(Address.class))).thenReturn(createValidAddress());
		BDDMockito.doNothing().when(addressServiceMock).replace(ArgumentMatchers.any(Address.class));
	}
	
	@Test
	void list_ReturnsListOfAddress_WhenSuccessful() {
		String expectedName = createAddressToBeSaved().getStreetName();
		List<Address> address = addressController.list().getBody();
		
		Assertions.assertThat(address.get(0).getStreetName()).isEqualTo(expectedName);
		Assertions.assertThat(address).isNotNull()
									.isNotEmpty()
									.hasSize(1);
	}
	
	@Test
	void findById_ReturnsListOfAddress_WhenSuccessful() {
		Long expectedId = createValidAddress().getId();
		Address address = addressController.findById(1).getBody();
		
		Assertions.assertThat(address.getId()).isEqualTo(expectedId);
		Assertions.assertThat(address).isNotNull();
	}
	
	@Test
	void save_ReturnsListOfAddress_WhenSuccessful() {
		Address address = addressController.save(createNewAddressToBeSaved()).getBody();
		
		Assertions.assertThat(address.getId()).isNotNull();
	}
	
	@Test
	void replace_ReturnsListOfAddress_WhenSuccessful() {
		Assertions.assertThatCode(() -> addressController.replace(createValidUpdateAddress())).doesNotThrowAnyException();
		ResponseEntity<Void> entity = addressController.replace(createValidUpdateAddress());
		Assertions.assertThat(entity).isNotNull();
		Assertions.assertThat(entity.getStatusCodeValue()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
	
	private static Address createAddressToBeSaved() {
		return Address.builder()
				.latitude(100000l)
				.longitude(20000l)
				.complement("Casa")
				.zipcode("34526000")
				.streetName("Brasilia")
				.number("125b")
				.neighbourhood(Neighbourhood.builder().name("Centro").build())
				.city(City.builder().name("Belo Horizonte").build())
				.state(State.builder().name("MG").build())
				.country(Country.builder().name("Brasil").build())
				.build();
	}
	
	private static Address createNewAddressToBeSaved() {
		return Address.builder()
				.latitude(202020l)
				.longitude(20000l)
				.complement("Casa")
				.zipcode("34554111")
				.streetName("São Paulo")
				.number("15598")
				.neighbourhood(Neighbourhood.builder().name("Centro").build())
				.city(City.builder().name("Santos").build())
				.state(State.builder().name("SP").build())
				.country(Country.builder().name("Brasil").build())
				.build();
	}
	
	private static Address createValidAddress() {
		return Address.builder()
				.id(1l)
				.streetName("Brasilia")
				.build();
	}
	
	private static Address createValidUpdateAddress() {
		return Address.builder()
				.id(1l)
				.streetName("Contorno")
				.build();
	}

}
