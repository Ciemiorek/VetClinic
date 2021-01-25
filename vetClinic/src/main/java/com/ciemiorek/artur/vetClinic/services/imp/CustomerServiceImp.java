package com.ciemiorek.artur.vetClinic.services;

import com.ciemiorek.artur.vetClinic.api.request.AppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.response.CreateAppointmentResponse;
import com.ciemiorek.artur.vetClinic.models.Appointment;
import org.springframework.http.ResponseEntity;

public class CustomerServices {
    public ResponseEntity<CreateAppointmentResponse> createAppointment(AppointmentRequest appointment) {
        return null;
    }
}
