package com.ciemiorek.artur.vetClinic.api.response;

import com.ciemiorek.artur.vetClinic.models.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ListOfAppointmentByDoctorResponse extends BasicResponse {

    private String doctorNameAndSurname;
    private List<Appointment> appointmentList;


    public ListOfAppointmentByDoctorResponse(String responseMsg, String doctorNameAndSurname, List<Appointment> appointmentList) {
        super(responseMsg);
        this.doctorNameAndSurname = doctorNameAndSurname;
        this.appointmentList = appointmentList;
    }
}
