package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="appointments")


public class Appointment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="doctor_id",nullable = false)
    private Doctor doctor;


    @ManyToOne

    @JoinColumn(name="patient_id",nullable = false)
    private Patient patient;

    @OneToOne
    @JoinColumn(name="invoice_id",nullable = false)
    private Invoice invoice;


    @JoinColumn(name=”prescription_id,nullable=false)
    private Prescription prescription;
    private LocalDateTime created at;
    private LocalDate localdate;
    private String room;
    @Enumerated(EnumType.STRING)
    private appoinmentStatus status;

    @JoinColumn(name=”prescription_id,nullable=false)
    private Prescription prescription;
    private  LocalDateTime created at;
    private LocalDate localdate;
    private String room;
    @Enumerated(EnumType.STRING)
    private appoinmentStatus status;

}
