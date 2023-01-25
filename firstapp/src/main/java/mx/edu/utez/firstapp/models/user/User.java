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
    @Column(nullable = false, unique = true, length = 30)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private String lastAccess;
    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 0")
    private Boolean blocked;
    @Column(nullable = false)
    private String token;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id", referencedColumnName = "id",
    unique = true)
    private Person person;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
