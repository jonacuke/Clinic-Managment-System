package Clinic.Managment.System.Clinic.Managment.System.Repository;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Patient;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(Long id);
}
