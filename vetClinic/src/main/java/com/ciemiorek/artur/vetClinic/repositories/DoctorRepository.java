package com.ciemiorek.artur.vetClinic.repositories;

import com.ciemiorek.artur.vetClinic.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
