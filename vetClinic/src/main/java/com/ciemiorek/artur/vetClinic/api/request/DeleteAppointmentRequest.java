package com.ciemiorek.artur.vetClinic.api.request;

import lombok.Getter;

@Getter
public class DeleteAppointmentRequest {

    private long customerId;
    private String customerIdAuthorization;
    private String customerPin;
    private long appointmentId;
}
