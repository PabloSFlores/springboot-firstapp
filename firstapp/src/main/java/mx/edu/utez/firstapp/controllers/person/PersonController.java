package mx.edu.utez.firstapp.controllers.person;

import mx.edu.utez.firstapp.dto.PersonDto;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.services.person.PersonService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-firstapp/person/")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired
    private PersonService service;

    //Obtener todos los registros de people
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Person>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    //Obtener un registro de people
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Person>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    //Insertar a una persona
    @PostMapping
    public ResponseEntity<CustomResponse<Person>> insert(
        @Valid @RequestBody PersonDto personDto){
        return new ResponseEntity<>(
            this.service.insert(personDto.getPerson()),
            HttpStatus.CREATED
        );
    }

    //Modificar una persona
    @PutMapping("/")
    public ResponseEntity<CustomResponse<Person>> update(
        @RequestBody PersonDto personDto,@Valid BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(
                null,
                HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
            this.service.update(personDto.getPerson()),
            HttpStatus.CREATED
        );
    }

    //Modificar el status de una persona
    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(
            @RequestBody PersonDto personDto){
        return new ResponseEntity<>(
                this.service.changeStatus(personDto.getPerson()),
                HttpStatus.OK
        );
    }


    //Curso
    // @PostMapping("/")
    // public ResponseEntity<CustomResponse<Message>> savePerson(@RequestBody Person person){
    //     service.savePerson(person);
    //     return new ResponseEntity<>(
    //             new CustomResponse<>(
    //                     new Message("Persona insertada"),
    //                     false,
    //                     200,
    //                     "Ok"
    //             ),
    //             HttpStatus.OK
    //     );
    // }
}
