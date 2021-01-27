package com.ciemiorek.artur.vetClinic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Appointment implements Comparable<Appointment> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private Doctor doctor;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    @Override
    public int compareTo(Appointment appointment) {
        if (date.equals(appointment.getDate())){
            return startTime.compareTo(appointment.getStartTime());
        } else return date.compareTo(appointment.getDate());
    }

}
