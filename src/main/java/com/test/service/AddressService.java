package com.test.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.domain.Address;
import com.test.exeception.BadRequestExeception;
import com.test.repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

	private final AddressRepository addressRepository;
	
	public List<Address> listAll() {
		return addressRepository.findAll();
	}
	
	public Address findByIdOrThrowBadRequestException(long id) {
		return addressRepository.findById(id).orElseThrow(() -> new BadRequestExeception("Address Not Found"));
	}
	
	@Transactional
	public Address save(Address address) {
		return addressRepository.save(address);
	}
	
	@Transactional
	public void delete(long id) {
		addressRepository.delete(findByIdOrThrowBadRequestException(id));
	}
	
	@Transactional
	public void replace(Address address) {
		Address item = findByIdOrThrowBadRequestException(address.getId());
		address.setId(item.getId());
		addressRepository.save(address);
	}
}
