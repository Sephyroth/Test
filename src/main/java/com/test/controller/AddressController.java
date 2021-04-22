package com.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.domain.Address;
import com.test.service.AddressService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/address")
public class AddressController {

	private final AddressService addressService;
	
	@GetMapping
	public ResponseEntity<List<Address>> list() {
		log.info("list all address");
		return ResponseEntity.ok(addressService.listAll());
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Address> findById(@PathVariable long id) {
		log.info("searching item of id: {}", id);
		return ResponseEntity.ok(addressService.findByIdOrThrowBadRequestException(id));
	}
	
	@PostMapping
	public ResponseEntity<Address> save(@RequestBody @Valid Address address) {
		log.info("save item: {}", address.toString());
		return new ResponseEntity<>(addressService.save(address), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		log.info("delete item for id: {}", id);
		addressService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> replace(@RequestBody Address address) {
		log.info("changing the item: {}", address);
		addressService.replace(address);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
