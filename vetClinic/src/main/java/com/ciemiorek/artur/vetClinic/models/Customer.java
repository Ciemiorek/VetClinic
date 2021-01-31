package com.ciemiorek.artur.vetClinic.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private String idForAuthorization;
    private String pin;

    @OneToMany
    private Set<Appointment> appointments;


}
