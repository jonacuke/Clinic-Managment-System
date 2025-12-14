
package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    private LocalDateTime createdAt;

    private LocalDate localDate;

    private String room;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
