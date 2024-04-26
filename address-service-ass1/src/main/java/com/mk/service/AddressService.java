package com.mk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.entity.Address;
import com.mk.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;

	public Address saveAddress(Address address) {
		return repository.save(address);
	}

	public Address findAddressById(Integer addressId) {
		Optional<Address> optional = repository.findById(addressId);
		if (optional.isEmpty()) {
			return null;
		}
		Address address = optional.get();
		return address;
	}

	public Address updateAddressById(Integer addressId) {
		Optional<Address> optional = repository.findById(addressId);
		if (optional.isEmpty()) {
			return null;
		}
		Address address = optional.get();
		address.setStreet("New Colony");
		repository.save(address);
		return address;
	}

	public Address deleteAddressById(Integer addressId) {
		Optional<Address> optional = repository.findById(addressId);
		if (optional.isEmpty()) {
			return null;
		}
		Address address = optional.get();
		repository.deleteById(addressId);
		return address;
	}
}