package com.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.entity.Address;
import com.mk.service.AddressService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService service;

	@PostMapping("/save")
	public Address saveAddress(@RequestBody Address address) {
		return service.saveAddress(address);
	}

	@GetMapping("/id{id}")
	public Address findAddressById(@PathVariable("id") Integer addressId) {
		return service.findAddressById(addressId);
	}

	@PutMapping("update{id}")
	public Address updateAddressById(@PathVariable("id") Integer addressId) {
		return service.updateAddressById(addressId);
	}

	@DeleteMapping("delete{id}")
	public Address deleteAddressById(@PathVariable("id") Integer addressId) {
		return service.deleteAddressById(addressId);
	}

	@GetMapping("/show")
	@CircuitBreaker(name = "circuit-breaker", fallbackMethod = "addressNotFound")
	public String showPage() {
		throw new RuntimeException("CHECK!.");
	}

	public String addressNotFound(Exception e) {
		return "Address Not Found";
	}
}