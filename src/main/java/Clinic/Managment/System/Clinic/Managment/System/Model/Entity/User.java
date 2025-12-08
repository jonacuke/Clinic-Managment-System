package Clinic.Managment.System.Clinic.Managment.System.Model.Entity;

import Clinic.Managment.System.Clinic.Managment.System.Model.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String username;
    @Column(nullable=false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
