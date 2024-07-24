package com.credmarg.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.model.Employees;
import com.credmarg.model.Vendors;
import com.credmarg.service.EmailService;
import com.credmarg.service.UserService;
import com.credmarg.service.VendorService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService adminService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VendorService vendorService;

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/vendors/{adminId}")
	public ResponseEntity<Set<Vendors>> getAllVendorsByAdmin(@PathVariable int adminId) {
		Set<Vendors> vendors = adminService.getAllVendorsByAdmin(adminId);
		return ResponseEntity.ok(vendors);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/employees/{adminId}")
	public ResponseEntity<Set<Employees>> getAllEmployeesByAdmin(@PathVariable int adminId) {
		Set<Employees> employees = adminService.getAllEmployeesByAdmin(adminId);
		return ResponseEntity.ok(employees);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/vendors/{adminId}")
	public ResponseEntity<Vendors> createVendor(@RequestBody Vendors vendor, @PathVariable int adminId) {
		Vendors createdVendor = adminService.createVendor(vendor, adminId);
		return ResponseEntity.ok(createdVendor);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/employees/{adminId}")
	public ResponseEntity<Employees> createEmployee(@RequestBody Employees employee, @PathVariable int adminId) {
		Employees createdEmployee = adminService.createEmployee(employee, adminId);
		return ResponseEntity.ok(createdEmployee);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/notify")
	public void notifyVendors(@RequestBody List<String> vendorEmails) {
		for (String email : vendorEmails) {
			Vendors vendor = vendorService.findByEmail(email);
			if (vendor != null) {
				emailService.sendTemplatedEmail(vendor.getEmail(), "Payment Notification",
						"Sending payments to vendor %s at UPI %s", vendor.getName(), vendor.getUpi());
			} else {
				System.out.println("Vendor with email " + email + " not found.");
			}
		}
	}
	
}
