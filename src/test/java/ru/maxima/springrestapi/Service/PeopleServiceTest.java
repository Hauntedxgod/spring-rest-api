package ru.maxima.springrestapi.Service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import ru.maxima.springrestapi.models.Person;
import ru.maxima.springrestapi.repositories.PeopleRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PeopleServiceTest {


    @Autowired
     PeopleRepository repository;
//    ModelMapper modelMapper = new ModelMapper();
//
//    @Mock
//    PeopleService service = new PeopleService(repository , modelMapper);

    Person person = new Person();

    @BeforeEach
    public void setUp (){
        person.setId(102L);
        person.setName("maxim");
        person.setAge(15);
        person.setEmail("test@mail.ru");
    }


    @Test
    void exceptingThatWeHaveConnectionToBDTest() {
        List<Person> people = repository.findAll();
        assertNotNull(people);
    }

    @Test
    void saveNewPersonExceptionNotNull() {
        repository.save(person);
        assertNotNull(repository.findById(102L));
    }

    @AfterEach
    public void tearsDown(){
        repository.deleteById(102L);
    }

    @Test
    void deleteByIdExpectingCorrectRemove() {
        repository.deleteById(102L);

        assertNull(repository.findById(102L).orElse(null));
    }
}