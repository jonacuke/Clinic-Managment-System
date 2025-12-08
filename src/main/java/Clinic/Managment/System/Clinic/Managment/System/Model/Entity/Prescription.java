package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name=”prescriptions”)
public class Prescriptions{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name=”prescpription_id,nullable=false)
    private Prescription prescription;
    private String medicament;
    private double doza;
    private String instructions;
}










public class Prescription {
}
