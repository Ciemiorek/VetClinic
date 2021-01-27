package com.ciemiorek.artur.vetClinic.services;

import com.ciemiorek.artur.vetClinic.api.request.DeleteAppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.response.BasicResponse;
import com.ciemiorek.artur.vetClinic.common.MsgSource;
import com.ciemiorek.artur.vetClinic.models.Appointment;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.TreeSet;

@AllArgsConstructor
public abstract class AbstractCommonService {

    protected MsgSource msgSource;



}
