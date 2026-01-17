package Clinic.Managment.System.Clinic.Managment.System.Repository;

import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.Doctor;
import Clinic.Managment.System.Clinic.Managment.System.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByUser(User user);
    Optional<Doctor> findByUserId(Long userId);
    boolean existsByUser(User user);
    List<Doctor> findBySpecialization(String specialization);
}
