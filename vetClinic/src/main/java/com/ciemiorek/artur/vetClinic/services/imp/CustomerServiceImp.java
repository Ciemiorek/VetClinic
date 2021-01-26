package com.ciemiorek.artur.vetClinic.services.imp;

import com.ciemiorek.artur.vetClinic.api.request.AppointmentRequest;
import com.ciemiorek.artur.vetClinic.api.response.CreateAppointmentResponse;
import com.ciemiorek.artur.vetClinic.api.response.ListOfAppointmentByDoctorResponse;
import com.ciemiorek.artur.vetClinic.common.MsgSource;
import com.ciemiorek.artur.vetClinic.excepiton.CommonConflictException;
import com.ciemiorek.artur.vetClinic.models.Appointment;
import com.ciemiorek.artur.vetClinic.models.Customer;
import com.ciemiorek.artur.vetClinic.models.Doctor;
import com.ciemiorek.artur.vetClinic.repositories.AppointmentRepository;
import com.ciemiorek.artur.vetClinic.repositories.CustomerRepository;
import com.ciemiorek.artur.vetClinic.repositories.DoctorRepository;
import com.ciemiorek.artur.vetClinic.services.AbstractCommonService;
import com.ciemiorek.artur.vetClinic.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CustomerServiceImp extends AbstractCommonService implements CustomerService {

    private CustomerRepository customerRepository;
    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;

    public CustomerServiceImp(MsgSource msgSource, CustomerRepository customerRepository, AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        super(msgSource);
        this.customerRepository = customerRepository;
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }


    @Override
    @Transactional
    public ResponseEntity<CreateAppointmentResponse> createAppointment(AppointmentRequest appointmentRequest) {
        checkPinAndIdAuto(appointmentRequest);
        checkIsDateFuture(parsStringToLocalDate(appointmentRequest.getDate()));
        Appointment appointment = requestToAppointment(appointmentRequest);
        checkIsDoctorIsFree(appointmentRequest);
        appointment = appointmentRepository.save(appointment);
        System.out.println(appointment.getId());

        return ResponseEntity.ok(new CreateAppointmentResponse(msgSource.OK001,appointment.getId()));
    }


    @Override
    @Transactional
    public ResponseEntity<ListOfAppointmentByDoctorResponse> getDoctorAppointmentAtDay(Long id, String date) {

        Doctor doctor = getDoctorFromRepository(id);
        List<Appointment> appointmentList = extractDoctorsAllAppointmentsByDay(id,date);
        String namePlusSurname = doctor.getName() + " " + doctor.getSurname();
        ListOfAppointmentByDoctorResponse response = new ListOfAppointmentByDoctorResponse(msgSource.OK002, namePlusSurname, appointmentList);

        return ResponseEntity.ok(response);
    }

    private Customer getCustomersFromRepository(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            throw new CommonConflictException(msgSource.Err001);
        }
        return optionalCustomer.get();
    }

    private List<Appointment> extractDoctorsAllAppointmentsByDay(Long doctorId,String date) {
        List<Appointment> appointmentList = appointmentRepository.findByDoctorId(doctorId);
        LocalDate localDate = parsStringToLocalDate(date);
        if (appointmentList.isEmpty()) {
            throw new CommonConflictException(msgSource.Err004);
        }
        List<Appointment> appointmentOnDayList = appointmentList.stream()
                .filter(c -> c.getDate().equals(localDate))
                .sorted()
                .collect(Collectors.toList());
        if (appointmentOnDayList.isEmpty()) {
            throw new CommonConflictException(msgSource.Err004);
        }
        return appointmentOnDayList;
    }

    private LocalDate parsStringToLocalDate(String date) {

        checkDateFormat(date);
        String[] dateInArray = date.split("-");
        int year = Integer.valueOf(dateInArray[0]);
        int month = Integer.valueOf(dateInArray[1]);
        int day = Integer.valueOf(dateInArray[2]);
        LocalDate localDate = LocalDate.of(year, month, day);


        return localDate;
    }

    private void checkIsDateFuture(LocalDate localDate){
        if (0>localDate.compareTo(LocalDate.now())){
            throw new CommonConflictException(msgSource.Err007);
        }
    }

    private void checkDateFormat(String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new CommonConflictException(msgSource.Err002);
        }
    }

    private Doctor getDoctorFromRepository(Long id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (!optionalDoctor.isPresent()) {
            throw new CommonConflictException(msgSource.Err003);
        } else {
            return optionalDoctor.get();
        }
    }

    private LocalTime parsStringToLocalTime(String time) {
        if (!time.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
            throw new CommonConflictException(msgSource.Err005);

        }
        return LocalTime.parse(time);
    }

    private Appointment requestToAppointment(AppointmentRequest appointmentRequest) {
        Appointment appointment = new Appointment();
        appointment.setDate(parsStringToLocalDate(appointmentRequest.getDate()));
        appointment.setDoctor(getDoctorFromRepository(appointmentRequest.getDoctorId()));
        appointment.setCustomer(getCustomersFromRepository(appointmentRequest.getCustomerId()));
        appointment.setStartTime(parsStringToLocalTime(appointmentRequest.getStartTime()));
        appointment.setEndTime(parsStringToLocalTime(appointmentRequest.getEndTime()));
        return appointment;
    }

    private void checkPinAndIdAuto(AppointmentRequest appointmentRequest){
        Customer customer = getCustomersFromRepository(appointmentRequest.getCustomerId());
        if (!(customer.getPin().equals(appointmentRequest.getCustomerPin())||customer.getIdForAuto().equals(appointmentRequest.getCustomerIdAuto()))){
            throw new CommonConflictException(msgSource.Err006);
        }
    }

    private void checkIsDoctorIsFree(AppointmentRequest appointmentRequest){
       try {
        extractDoctorsAllAppointmentsByDay(appointmentRequest.getDoctorId(), appointmentRequest.getDate());
       }catch (Exception e){
           return;
       }
        List<Appointment> doctorAppointmentsList = extractDoctorsAllAppointmentsByDay(appointmentRequest.getDoctorId(), appointmentRequest.getDate());

        LocalTime start = parsStringToLocalTime(appointmentRequest.getStartTime());
        LocalTime end = parsStringToLocalTime(appointmentRequest.getEndTime());
        for (Appointment appointment:doctorAppointmentsList){
            System.out.println("Data Spotkania "+appointment.getDate().toString());
            System.out.println("Spotkania juz umowione " + appointment.getStartTime().toString()+" "+appointment.getEndTime().toString());
            System.out.println("Spotkanie planowane" + start+"  "+ end);
            System.out.println("rożnica miedzy startem a koncem"+ appointment.getStartTime().compareTo(start)+"  "+appointment.getStartTime().compareTo(end));
        }


        doctorAppointmentsList =doctorAppointmentsList.stream()
                .filter(a->a.getStartTime().compareTo(start)==a.getStartTime().compareTo(end))
                .filter(a->a.getEndTime().compareTo(start)==a.getEndTime().compareTo(end))
                .filter(a->a.getStartTime().compareTo(start)==a.getEndTime().compareTo(start))
                .collect(Collectors.toList());


    }

}
