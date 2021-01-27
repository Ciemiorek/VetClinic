package com.ciemiorek.artur.vetClinic.controllers;

import com.ciemiorek.artur.vetClinic.api.request.AppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.request.DeleteAppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.response.BasicResponse;
import com.ciemiorek.artur.vetClinic.api.response.CreateAppointmentResponse;
import com.ciemiorek.artur.vetClinic.api.response.ListOfAppointmentByDoctorResponse;
import com.ciemiorek.artur.vetClinic.services.imp.CustomerServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerServiceImp customerServices;



    @PostMapping(value = "/create/appointment", produces = "application/json")
    public ResponseEntity<CreateAppointmentResponse> createAppointment(
            @RequestBody AppointmentRequest appointmentRequest)
    {return customerServices.createAppointment(appointmentRequest);}

    @GetMapping(value = "/appointment/{id}/{day}", produces = "application/json")
    public ResponseEntity<ListOfAppointmentByDoctorResponse> getAppointmentsOfDoctor(
            @PathVariable Long id, @PathVariable  String day)
    {return customerServices.getDoctorAppointmentAtDay(id,day);}

    @DeleteMapping(value = "/delete/appointment",produces = "application/json")
    public ResponseEntity<BasicResponse> deleteAppointment(
            @RequestBody DeleteAppointmentRequest deleteAppointmentRequest)
    {return customerServices.deleteAppointment(deleteAppointmentRequest);}




}
