package com.credmarg.service;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.credmarg.exception.ResourceNotFoundException;
import com.credmarg.model.Employees;
import com.credmarg.model.Role;
import com.credmarg.model.User;
import com.credmarg.model.Vendors;
import com.credmarg.repository.EmployeeRepository;
import com.credmarg.repository.UserRepository;
import com.credmarg.repository.VendorRepository;

@Service
public class UserService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private VendorRepository vendorsRepo;

	@Autowired
	private EmployeeRepository employeesRepo;

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	// delete user
	public void deleteUser(int id) {
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		userRepo.delete(user);
	}

	// get all vendors created by admin
	public Set<Vendors> getAllVendorsByAdmin(int adminId) {
		User admin = userRepo.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + adminId));
		return admin.getVendors();
	}

	// get all employees created by admin
	public Set<Employees> getAllEmployeesByAdmin(int adminId) {
		User admin = userRepo.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + adminId));
		return admin.getEmployees();
	}

	// create and save vendor
	public Vendors createVendor(Vendors vendor, int adminId) {
		User admin = userRepo.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + adminId));
		vendor.setCreatedBy(admin);
		return vendorsRepo.save(vendor);
	}

	// create and save employee
	public Employees createEmployee(Employees employee, int adminId) {
		User admin = userRepo.findById(adminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin not found with id " + adminId));
		employee.setCreatedBy(admin);
		return employeesRepo.save(employee);
	}

//	public User getSingleData(String email) {
//		return userRepository.findByEmailIgnoreCase(email);
//	}

	public User create(User user) {
		// Encrypt the user's password
		String password = user.getPassword();
		String encrypt = bCryptPasswordEncoder.encode(password);
		user.setPassword(encrypt);
		user.setDate(new Date());

		// Set roles for the user
		Set<Role> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleName("ADMIN");
		roles.add(role);
		user.setRoles(roles);

		return userRepository.save(user);
	}

	// get current user

	public User getCurrentUser(Principal principal) {
		String username = principal.getName();
		return userRepository.findByEmailIgnoreCase(username);
	}
}
