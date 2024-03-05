package ru.maxima.springrestapi.dto;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import ru.maxima.springrestapi.models.Person;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDTO {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //относится ко многим
    @JoinColumn(name = "person_id", referencedColumnName = "id") // ссылается на id
    private Person owner;
}
