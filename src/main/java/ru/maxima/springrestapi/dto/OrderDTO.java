package ru.maxima.springrestapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.maxima.springrestapi.models.Person;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDTO {


    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;


    @JsonIgnore
    private Person owner;
}
