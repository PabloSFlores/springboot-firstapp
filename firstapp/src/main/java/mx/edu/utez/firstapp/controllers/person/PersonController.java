package mx.edu.utez.firstapp.controllers.person;

import mx.edu.utez.firstapp.dto.Message;
import mx.edu.utez.firstapp.dto.PersonDto;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.services.person.PersonService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-firstapp/person/")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Person>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Message>> savePerson(@RequestBody Person person){
        service.savePerson(person);
        return new ResponseEntity<>(
                new CustomResponse<>(
                        new Message("Persona insertada"),
                        false,
                        200,
                        "Ok"
                ),
                HttpStatus.OK
        );
    }
}
