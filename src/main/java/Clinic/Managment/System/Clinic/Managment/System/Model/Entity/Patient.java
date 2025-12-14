package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Past
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;
}
