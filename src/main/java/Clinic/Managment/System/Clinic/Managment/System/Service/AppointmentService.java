package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Appointment;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.AppointmentStatus;
import Clinic.Managment.System.Clinic.Managment.System.Repository.AppointmentRepository;
import Clinic.Managment.System.Clinic.Managment.System.Repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Appointment> getAppointmentsByDoctor(User doctorUser) {
        if (doctorUser == null) {
            return Collections.emptyList();
        }

        Doctor doctor = doctorRepository.findByUser(doctorUser).orElse(null);

        if (doctor == null) {
            return Collections.emptyList();
        }

        return appointmentRepository.findByDoctorId(doctor.getId());
    }
    public boolean doctorProfileExists(User user) {
        if (user == null) return false;
        return doctorRepository.existsByUser(user);
    }

    public Appointment addAppointment(User doctorUser, Appointment appointment) {
        if (doctorUser == null || appointment == null) {
            throw new IllegalArgumentException("Të dhëna të pavlefshme!");
        }

        Doctor doctor = doctorRepository.findByUser(doctorUser).orElse(null);
        if (doctor == null) {
            throw new IllegalArgumentException("Profili i doktorit nuk ekziston!");
        }

        appointment.setDoctor(doctor);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointmentStatus(User doctorUser, Long appointmentId, String status) {
        if (doctorUser == null || appointmentId == null || status == null) {
            throw new IllegalArgumentException("Të dhëna të pavlefshme!");
        }

        Doctor doctor = doctorRepository.findByUser(doctorUser).orElse(null);
        if (doctor == null) {
            throw new IllegalArgumentException("Profili i doktorit nuk ekziston!");
        }

        Appointment appointment = appointmentRepository.findByIdAndDoctorId(appointmentId, doctor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Takimi nuk ekziston"));

        AppointmentStatus appointmentStatus;
        try {
            appointmentStatus = AppointmentStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status i pavlefshëm!");
        }

        appointment.setStatus(appointmentStatus);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(User doctorUser, Long appointmentId) {
        if (doctorUser == null || appointmentId == null) {
            throw new IllegalArgumentException("Të dhëna të pavlefshme!");
        }

        Doctor doctor = doctorRepository.findByUser(doctorUser).orElse(null);
        if (doctor == null) {
            throw new IllegalArgumentException("Profili i doktorit nuk ekziston!");
        }

        Appointment appointment = appointmentRepository.findByIdAndDoctorId(appointmentId, doctor.getId())
                .orElseThrow(() -> new IllegalArgumentException("Takimi nuk ekziston"));
        appointmentRepository.delete(appointment);
    }

}
