package fr.ludotheque.controller;


import fr.ludotheque.dao.IUserDao;
import fr.ludotheque.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private IUserDao userDao;

    @RequestMapping(path="/user", method = RequestMethod.GET)
    public ResponseEntity<List<User>>  listUser(){
        return new ResponseEntity<List<User>>(getUsers(), HttpStatus.OK);
    }

    @RequestMapping(path="/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User>  listUser(@PathVariable(value = "id") String id){
        return new ResponseEntity<User>(getUsers().stream().filter(user -> Integer.toString(user.getId()).equals(id)).findFirst().orElse(null), HttpStatus.OK);

    }

    @RequestMapping(path="/user", method = RequestMethod.POST)
    public ResponseEntity<String>  listUser(@RequestBody User user){
        return new ResponseEntity<String>("18", HttpStatus.OK);
    }

    private List<User> getUsers() {
        return userDao.findAll();
    }

    @RequestMapping(value="/logmeout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
