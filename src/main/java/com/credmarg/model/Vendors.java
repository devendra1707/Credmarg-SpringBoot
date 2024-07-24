package com.credmarg.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Vendors {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vendorId;
	private String name;
	private String email;
	private String upi;
	
	@ManyToOne
	 @JsonBackReference
    private User createdBy;
}
