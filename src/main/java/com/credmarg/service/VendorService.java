package com.credmarg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.model.Vendors;
import com.credmarg.repository.VendorRepository;

@Service
public class VendorService {

	@Autowired
	private VendorRepository vendorRepository;

	public Vendors findByEmail(String email) {
		return vendorRepository.findByEmail(email).orElse(null);
	}
}
