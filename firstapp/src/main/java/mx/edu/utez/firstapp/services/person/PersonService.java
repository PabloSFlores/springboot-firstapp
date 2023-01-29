package mx.edu.utez.firstapp.services.person;

import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.models.person.PersonRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository repository;

    //Servicio para obtener todos los registros de people
    @Transactional(readOnly = true)
    public CustomResponse<List<Person>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
            200,
            "Ok"
        );
    }

    //Servicio para obtener a una persona
    @Transactional(readOnly = true)
    public CustomResponse<Person> getOne(Long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    //Servicio para insertar a una persona
    //Valida la existencia por curp
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> insert(Person person){
        Optional<Person> exists = this.repository.findByCurp(person.getCurp());
        if(exists.isPresent()){
            return new CustomResponse<>(
                null,
                true,
                400,
                "La persona ya se encuentra registrada"
            );
        }
        return new CustomResponse<>(
            this.repository.saveAndFlush(person),
            false,
            200,
            "Persona registrada correctamente"
        );
    }

    //Servicio para modificar a una persona
    //Valida la existencia del registro por id
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Person> update(Person person){
        if(!this.repository.existsById(person.getId())){
            return new CustomResponse<>(
                null,
                true,
                400,
                "La persona no existe"
            );
        }
        return new CustomResponse<>(
            this.repository.saveAndFlush(person),
            false,
            200,
            "Persona actualizada correctamente"
        );
    }

    //Servicio para cambiar el status de una persona
    //Valida existencia por curp
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Person person){
        if(!this.repository.existsById(person.getId())){
            return new CustomResponse<>(
                0,
                true,
                400,
                "La persona no existe"
            );
        }
        return new CustomResponse<>(
            this.repository.updateStatusById(
                    person.getStatus(), person.getId()
            ),
            false,
            200,
            "Persona actualizada correctamente"
        );
    }
}
