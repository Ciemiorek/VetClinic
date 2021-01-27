package com.ciemiorek.artur.vetClinic.services;

import com.ciemiorek.artur.vetClinic.api.request.AppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.request.DeleteAppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.response.BasicResponse;
import com.ciemiorek.artur.vetClinic.api.response.CreateAppointmentResponse;
import com.ciemiorek.artur.vetClinic.api.response.ListOfAppointmentByDoctorResponse;
import com.ciemiorek.artur.vetClinic.models.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {
    ResponseEntity<CreateAppointmentResponse> createAppointment(AppointmentRequest request);
    ResponseEntity<ListOfAppointmentByDoctorResponse> getDoctorAppointmentAtDay(Long id, String day);
    ResponseEntity<BasicResponse> deleteAppointment(DeleteAppointmentRequest deleteAppointmentRequest);


}
