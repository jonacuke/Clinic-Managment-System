package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="invoices")

public class Invoice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="appointment_id",nullable=false)
    private Appointment appointment;
    private double total;
    private Boolean isPaid;//

}
