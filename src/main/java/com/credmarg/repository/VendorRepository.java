package com.credmarg.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credmarg.model.Vendors;

@Repository
public interface VendorRepository extends JpaRepository<Vendors, Integer> {

	Optional<Vendors> findByEmail(String email);
}
