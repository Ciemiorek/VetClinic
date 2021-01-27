package com.ciemiorek.artur.vetClinic.api.request;


import com.ciemiorek.artur.vetClinic.models.Customer;
import com.ciemiorek.artur.vetClinic.models.Doctor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
public class AppointmentRequest {

    private long doctorId;
    private long customerId;
    private String customerIdAuthorization;
    private String customerPin;
    private String date;
    private String startTime;
    private String endTime;


}
