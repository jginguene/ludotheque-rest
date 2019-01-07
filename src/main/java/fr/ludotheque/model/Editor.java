package fr.ludotheque.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class Editor {

    @Id
    @GeneratedValue
    private int id;

    @Length(min = 5, max = 32)
    private String name;


}
