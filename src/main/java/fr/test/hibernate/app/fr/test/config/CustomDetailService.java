package fr.test.hibernate.app.fr.test.config;

import java.util.ArrayList;
import java.util.List;

import fr.test.hibernate.app.dao.IUserDao;
import fr.test.hibernate.app.model.User;
import fr.test.hibernate.app.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private IUserDao userService;

    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String userName)
        throws UsernameNotFoundException {

        User user = userService.findByUserNameEquals(userName);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
            true,
            true,
            true,
            true,
            getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(UserProfile userProfile : user.getProfiles()){
            System.out.println("UserProfile : "+userProfile);
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile));
        }
        System.out.print("authorities :"+authorities);
        return authorities;
    }

}
