package com.restaurant.repositories;

import com.restaurant.commands.request.UserDTO;
import com.restaurant.commands.response.UserPrincipal;
import com.restaurant.models.User;
import com.restaurant.models.authority.Role;
import com.restaurant.models.authority.RoleName;
import com.restaurant.repositories.jpa.EmployeeJPARepository;
import com.restaurant.repositories.jpa.RoleJPARepository;
import com.restaurant.repositories.jpa.UserJPARepository;
import com.restaurant.utility.mappers.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJPARepository userJPARepository;

    private final RoleJPARepository roleJPARepository;

    private final EmployeeJPARepository employeeJPARepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<String> saveUser(UserDTO userDTO) {

        if (existsByUsername(userDTO.getUsername())) {
            return new ResponseEntity<>("Username already used", HttpStatus.BAD_REQUEST);
        }
        if (existsByEmail(userDTO.getEmail())) {
            return new ResponseEntity<>("Email already used", HttpStatus.BAD_REQUEST);
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleJPARepository
                .findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User role not found")));

        User user = new User(userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getEmail(),
                userDTO.getPhone(),
                roles, new HashSet<>());

        userJPARepository.save(user);
        return ResponseEntity.ok("User registered.");
    }

    @Override
    @Transactional
    public ResponseEntity deleteUser(Long userId) {
        userJPARepository.deleteById(userId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    @Override
    public ResponseEntity<UserPrincipal> updateUser(Long userId, UserDTO signupDTO) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found" + signupDTO.getUsername()));
        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        user.setEmail(signupDTO.getEmail());
        user.setPhone(signupDTO.getPhone());
        userJPARepository.save(user);
        return ResponseEntity.ok(UserMapper.mapUserToUserPrincipal(user));
    }

    @Override
    public UserPrincipal getUserById(Long userId) {
        return userJPARepository.findById(userId).map(UserMapper::mapUserToUserPrincipal).orElseThrow(() -> new UsernameNotFoundException("User not found" + userId));
    }

    @Override
    public List<UserPrincipal> getAllUsers() {
        return userJPARepository.findAll().stream().map(UserMapper::mapUserToUserPrincipal).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserPrincipal findByUsername(String username) {
        User user = userJPARepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return UserPrincipal.build(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userJPARepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userJPARepository.existsByEmail(email);
    }

}
