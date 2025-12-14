package Clinic.Managment.System.Clinic.Managment.System.Repository;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
