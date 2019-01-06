package fr.test.hibernate.app.dao;


import fr.test.hibernate.app.model.Game;
import fr.test.hibernate.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface  IUserDao extends JpaRepository<User, Integer> {

    User findByUserNameEquals(String userName);

}
