package fr.ludotheque.dao;


import fr.ludotheque.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface  IUserDao extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);

}
