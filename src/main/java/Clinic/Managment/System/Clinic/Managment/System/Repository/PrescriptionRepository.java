package Clinic.Managment.System.Clinic.Managment.System.Repository;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
