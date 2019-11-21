package com.restaurant.configuration.db;

import com.restaurant.models.User;
import com.restaurant.models.authority.Role;
import com.restaurant.models.authority.RoleName;
import com.restaurant.repositories.jpa.ReservationJPARepository;
import com.restaurant.repositories.jpa.RoleJPARepository;
import com.restaurant.repositories.jpa.UserJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class DbInit implements CommandLineRunner {

    private RoleJPARepository roleJPARepository;
    private PasswordEncoder passwordEncoder;
    private UserJPARepository userJPARepository;
    private ReservationJPARepository reservationJPARepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleJPARepository.findAll().isEmpty())
            roleJPARepository.saveAll(Arrays.asList(
                    new Role(RoleName.ROLE_ADMIN),
                    new Role(RoleName.ROLE_USER),
                    new Role(RoleName.ROLE_COOK),
                    new Role(RoleName.ROLE_WAITER))
            );

        if (!userJPARepository.existsByUsername("admin")) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleJPARepository
                            .findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error in DBINIT"))
            );
            roles.add(roleJPARepository
                    .findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error in DBINIT"))
            );

            User user = new User(
                    "admin",
                    passwordEncoder.encode("admin"),
                    "admin@pl.pl",
                    "admin",
                    roles,
                    new HashSet<>()
            );
            userJPARepository.save(user);
        }


//        //TEST
//        Reservation reservation1 = new Reservation( LocalDateTime.now().minusHours(1));
//        reservationJPARepository.save(reservation1);
//
//        Reservation reservation2 = new Reservation( LocalDateTime.now().plusHours(1));
//        reservationJPARepository.save(reservation2);

//        String str = "2019-11-16 15:15";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
//
//
//        System.out.println(reservationJPARepository.existsByDateReservationBetween(dateTime.minusHours(1),dateTime.plusHours(1)));


    }
}
