package com.restaurant.services;

import com.restaurant.commands.request.UserDTO;
import com.restaurant.commands.response.UserPrincipal;
import com.restaurant.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<String> createUser(UserDTO userDTO) {
        return userRepository.saveUser(userDTO);
    }

    @Override
    public ResponseEntity deleteUser(Long userId) {
        return userRepository.deleteUser(userId);
    }

    @Override
    public ResponseEntity<UserPrincipal> updateUser(Long userId, UserDTO userDTO) {
        return userRepository.updateUser(userId, userDTO);
    }

    @Override
    public UserPrincipal getUserById(Long userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<UserPrincipal> getAllUsers() {
        return userRepository.getAllUsers();
    }

}
