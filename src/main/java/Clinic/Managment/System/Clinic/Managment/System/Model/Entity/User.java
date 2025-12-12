package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true,nullable=false)//qe te jete unik asnje e dhene tjeter ne kete rresht nuk do jete e njejte
    private String userName;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDate birthday;

    private String email;

    private String lastName;
}
