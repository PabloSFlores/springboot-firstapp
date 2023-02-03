package mx.edu.utez.firstapp.models.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(unique = true, length = 30)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String lastAccess;
    @Column(columnDefinition = "TINYINT DEFAULT 0")
    private Boolean blocked;
    private String token;

    @OneToOne
    @JoinColumn(name = "person_id",
            referencedColumnName = "id",
            unique = true)
    @JsonIgnore
    private Person person;
    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;
}
