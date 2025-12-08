package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @JoinColumn(name=”appointment_id”,nullable=false)
    private Appointmet appointment;
    private String  name;
    private String surname;
    private String specialization;
    @OneToMany(mappedBy=”doctor”)
    private List <Appointment> appointments;
}

