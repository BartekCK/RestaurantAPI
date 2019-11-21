package com.restaurant.views;

import com.restaurant.models.User;
import com.restaurant.utility.mappers.ReservationMapper;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private List<ReservationView> reservationViews;

    private Collection<? extends GrantedAuthority> authorities;

    private UserPrincipal(Long id, String username, String email, String phone, String password, Collection<? extends GrantedAuthority> authorities, List<ReservationView> reservationViews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.authorities = authorities;
        this.reservationViews = reservationViews;
    }

    public static UserPrincipal build(User user) {

        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
                        .collect(Collectors.toList());
        List<ReservationView> reservationViews = user.getReservations().stream().map(ReservationMapper::mapReservationToReservationView).collect(Collectors.toList());
        return new UserPrincipal(
                user.getPersonId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                authorities,
                reservationViews
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserPrincipal user = (UserPrincipal) obj;
        return Objects.equals(id, user.id);
    }
}
