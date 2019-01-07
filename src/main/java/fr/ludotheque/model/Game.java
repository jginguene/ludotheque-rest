package fr.ludotheque.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Entity
// @JsonFilter("myDynamicGameFilter")
@Data
public class Game {

	@Id
	@GeneratedValue
	private int id;

	@Length(min = 5, max = 32)
	private String name;

	private int age;
	private int minPlayers;
	private int maxPlayers;
	private String category;
	private double price;


}
