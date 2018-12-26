package fr.test.hibernate.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.test.hibernate.app.model.Game;

@Repository
public interface IGameDao extends JpaRepository<Game, Integer> {

	List<Game> findByCategoryEquals(String category);

	@Query("SELECT g  FROM Game g WHERE g.price>= :price")
	List<Game> searchByPrice(@Param("price") double price);

}
