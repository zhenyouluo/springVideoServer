package com.khudim.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



public class UserService implements UserDetailsService {

    private static Logger logger = Logger.getLogger("service");

    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAll(){
        logger.debug("Retrieving all users");
        return userRepository.getAllUsers();
    }

    public User get(String name){
        return userRepository.getUser(name);
    }

    public void add(User user){
        userRepository.createUser(user);
    }

    public void delete(String name){
       userRepository.deleteUser(name);
    }

    public void edit(User user){
       userRepository.edit(user);
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUser(username);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User " + username + " not found.");
    }
}
