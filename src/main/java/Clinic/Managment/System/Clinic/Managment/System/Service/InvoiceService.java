package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Repository.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    private final AppointmentRepository appointmentRepository;

    public InvoiceService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
