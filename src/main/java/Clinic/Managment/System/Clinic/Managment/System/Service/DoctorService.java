package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Appointment;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.AppointmentStatus;
import Clinic.Managment.System.Clinic.Managment.System.Repository.AppointmentRepository;
import Clinic.Managment.System.Clinic.Managment.System.Repository.DoctorRepository;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public Doctor doctor(User user){
        return doctorRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Profili i doktorit nuk ekziston!"));
    }

    public List<Appointment> getAppointment(User user) {
        Doctor doctor = doctor(user);
        return appointmentRepository.findByDoctorId(doctor.getId());
    }

    public Appointment addAppointment(User doctorUser, Appointment appointment) {
        Doctor doctor = doctor(doctorUser);
        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointmentStatus(User doctorUser, Long appointmentId, String status) {
        Doctor doctor = doctor(doctorUser);
        Appointment appointment = appointmentRepository.findByIdAndDoctorId(appointmentId, doctor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment nuk ekziston"));

        AppointmentStatus appointmentStatus;
        try {
            appointmentStatus = AppointmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status i pavlefshëm!");
        }

        appointment.setStatus(appointmentStatus);
        return appointmentRepository.save(appointment);
    }
}


