package mx.edu.utez.firstapp.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.rol.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean status;
    private String lastAccess;
    private Boolean blocked;
    private String token;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id", referencedColumnName = "id",
    unique = true)
    private Person person;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
