package mx.edu.utez.firstapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.user.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 3, max = 50)
    private String name;
    private String surname;
    private String lastname;
    private String birthday;
    private String curp;
    private Boolean status;
    private String gender;
    private User user;

    public Person getPerson(){
        return new Person(
            getId(),
            getName(),
            getSurname(),
            getLastname(),
            getBirthday(),
            getCurp(),
            getStatus(),
            getGender(),
            getUser()
        );
    }
}
