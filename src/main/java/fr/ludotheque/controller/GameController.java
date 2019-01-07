package fr.ludotheque.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import fr.ludotheque.dao.IGameDao;
import fr.ludotheque.model.Game;
import fr.ludotheque.controller.exception.GameNotFoundException;

@RestController
public class GameController {

	@Autowired
	private IGameDao gameDao;

	@GetMapping(value = "/Games")
	public MappingJacksonValue listGames() {
		List<Game> games = gameDao.findAll();
		return filterGamesFields(games);
	}

	// Récupérer un produit par son Id
	@GetMapping(value = "/Games/{id}")
	public Game getGame(@PathVariable int id) {
		try {
			return gameDao.findById(id).get();
		} catch (java.util.NoSuchElementException e) {
			throw new GameNotFoundException("Game with id " + id + " not found.");
		}
	}

	@GetMapping(value = "/Games/category/{category}")
	public List<Game> getGameWithCategory(@PathVariable String category) {
		return gameDao.findByCategoryEquals(category);
	}

	@PutMapping(value = "/Games")
	public ResponseEntity<Void> addGame(@Valid @RequestBody Game game) {

		if (game == null) {
			return ResponseEntity.noContent().build();
		}

		Game gameAdded = gameDao.save(game);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gameAdded.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "/Games/{id}")
	public void deleteGame(@PathVariable int id) {
		gameDao.deleteById(id);
	}

	@GetMapping(value = "/Games/price/{price}")
	public List<Game> searchByPrice(@PathVariable double price) {
		return gameDao.searchByPrice(price);
	}

	/*
	 * @GetMapping(value = "/GamesV2/filter") public MappingJacksonValue
	 * getGamesWithoutPrices() { List<Game> games = gameDao.findAll(); return
	 * filterGamesFields(games, "price"); }
	 */

	public static MappingJacksonValue filterGamesFields(List<Game> games, String... excludedfields) {

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept(excludedfields);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myDynamicGameFilter", myFilter);
		MappingJacksonValue filteredGame = new MappingJacksonValue(games);
		filteredGame.setFilters(filterProvider);
		return filteredGame;

	}
}
