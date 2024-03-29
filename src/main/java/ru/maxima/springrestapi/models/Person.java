package ru.maxima.springrestapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor

@Getter
@Setter
@ToString
@Entity
@Table(name = "Person")
@Data
public class Person {

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;


    @Column(name = "email")
    private String email;


    @Column(name = "age")
    private Integer age;
//
    @Column(name = "admin")
    private boolean admin;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "created_by_person")
    private String createdByPerson;

    @OneToMany(mappedBy = "owner" , fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Order> orders;
    public Person() {

    }
}
