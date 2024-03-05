package ru.maxima.springrestapi.controllers;

import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maxima.springrestapi.Service.PeopleService;
import ru.maxima.springrestapi.dto.PersonDTO;
import ru.maxima.springrestapi.exceptions.PersonNotCreatedException;
import ru.maxima.springrestapi.exceptions.PersonNotFoundException;
import ru.maxima.springrestapi.exceptions.PersonNotFoundResponse;
import ru.maxima.springrestapi.exceptions.Response;
import ru.maxima.springrestapi.models.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PeopleController {

    private final PeopleService service;


    @Autowired
    public PeopleController(PeopleService service) {
        this.service = service;
    }
    @GetMapping()
    public List<PersonDTO> getAllPeople(){
        List<Person> allPeople = service.getAllPeople();
        List<PersonDTO> result = new ArrayList<>();
        allPeople.forEach(person -> {
            result.add(service.convertToPersonDTO(person));
        });
        return result;
    }
    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable Long id) throws PersonNotFoundException {
        return service.convertToPersonDTO(service.findById(id));
    }

    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<Object> handleException(PersonNotFoundException ex) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult result) {


        checkErrors(result);

        Person person = service.convertToPerson(personDTO);
        service.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") Long id,
                                             @RequestBody  @Valid Person person , BindingResult result) {

        checkErrors(result);

        Person updatedPerson = service.findById(id);
        service.update(id , updatedPerson);

        service.save(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public void checkErrors(BindingResult result){
        if (result.hasErrors()){

            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                builder.append(error.getField());
                builder.append("-");
                builder.append(error.getDefaultMessage());
            });
            throw new PersonNotCreatedException(builder.toString());

        }
    }

    @ExceptionHandler({PersonNotCreatedException.class})
    public ResponseEntity<Object> handleException(PersonNotCreatedException ex) {
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.BAD_REQUEST);

    }


}
