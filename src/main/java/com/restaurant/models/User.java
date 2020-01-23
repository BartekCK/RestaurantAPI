package com.restaurant.models;

import com.restaurant.models.authority.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToMany
    private Set<Role> roles;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Reservation> reservations;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.REMOVE)
    private Employee employee;

    public User(String username, @NotBlank String password, @Email String email, String phone, Set<Role> roles, Set<Reservation> reservations) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.reservations = reservations;
    }

    public Long getUserId() {
        return userId;
    }
}
