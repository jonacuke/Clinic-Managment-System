package Clinic.Managment.System.Clinic.Managment.System.Service;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import Clinic.Managment.System.Clinic.Managment.System.Repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    public Doctor getDoctorByUser(User user) {
        if (user == null) {
            return null;
        }
        return doctorRepository.findByUser(user).orElse(null);
    }
}


