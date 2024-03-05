package ru.maxima.springrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.springrestapi.models.Person;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person , Long> {
    List<Person> findAllByNameLike(String like);

    List<Person> findByName(String name);

    List<Person> findByEmail(String email);

    List<Person> findByNameOrEmail(String name , String email);

//    List<Person> findByNameStartingWith(String startingWith);
//
//    List<Person> findAllByNameStartingWithOrderByAge(String startingWith , Long age);

}
