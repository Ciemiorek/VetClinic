package com.ciemiorek.artur.vetClinic.repositories;

import com.ciemiorek.artur.vetClinic.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
