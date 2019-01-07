package fr.ludotheque.security;

import fr.ludotheque.dao.IUserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private IUserDao users;

    public CustomUserDetailsService(IUserDao users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails ret =this.users.findByUserName(username).get();
            if (ret == null){
                throw  new UsernameNotFoundException("Username: " + username + " not found");
            }else{
                return ret;
            }

     }
}
