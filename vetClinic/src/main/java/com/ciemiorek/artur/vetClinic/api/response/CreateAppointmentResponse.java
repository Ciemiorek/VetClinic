package com.ciemiorek.artur.vetClinic.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppointmentResponse extends BasicResponse {
    private Long appointmentId;

    public CreateAppointmentResponse(String responseMsg, Long appointmentId) {
        super(responseMsg);
        this.appointmentId = appointmentId;
    }
}
