package com.restaurant.repositories;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.commands.response.ReservationView;
import com.restaurant.models.Reservation;
import com.restaurant.models.Table;
import com.restaurant.models.User;
import com.restaurant.repositories.jpa.ReservationJPARepository;
import com.restaurant.repositories.jpa.TableJPARepository;
import com.restaurant.repositories.jpa.UserJPARepository;
import com.restaurant.utility.mappers.ReservationMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJPARepository reservationJPARepository;
    private final UserJPARepository userJPARepository;
    private final TableJPARepository tableTypeJPARepository;

    @Override
    public ResponseEntity<ReservationView> createReservationForUser(ReservationDTO reservationDTO, Long userId) {

        User user = userJPARepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Table table = tableTypeJPARepository.findById(reservationDTO.getTableId()).orElseThrow(() -> new RuntimeException("Table not found"));


        if (reservationJPARepository.existsByDateReservationBetweenAndTableEquals(reservationDTO.getDateReservation().minusHours(1), reservationDTO.getDateReservation().plusHours(1), table)) {
            //Check another LocalDataTime
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Reservation reservation = Reservation.builder()
                .dateReservation(reservationDTO.getDateReservation())
                .comments(reservationDTO.getComments())
                .table(table)
                .build();

        reservationJPARepository.save(reservation);
        user.getReservations().add(reservation);
        userJPARepository.save(user);

        return ResponseEntity.ok(ReservationMapper.mapReservationToReservationView(reservation));
    }

    @Override
    public ResponseEntity<ReservationView> getReservationById(Long reservationId) {
        return ResponseEntity.ok(reservationJPARepository.findById(reservationId).map(ReservationMapper::mapReservationToReservationView).orElseThrow(() -> new RuntimeException("Reservation not found")));
    }

    @Override
    public Iterable<ReservationView> getAllUserReservations(Long userId) {
        User user = userJPARepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getReservations().stream().map(ReservationMapper::mapReservationToReservationView).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity deleteReservation(Long reservationId) {
        if (reservationJPARepository.existsById(reservationId)) {
            reservationJPARepository.deleteById(reservationId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
