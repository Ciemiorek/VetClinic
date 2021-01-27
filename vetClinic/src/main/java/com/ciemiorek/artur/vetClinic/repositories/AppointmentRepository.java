package com.ciemiorek.artur.vetClinic.repositories;

import com.ciemiorek.artur.vetClinic.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByDoctorId(Long doctorId);
}
