package com.restaurant.repositories;

import com.restaurant.commands.request.ReservationDTO;
import com.restaurant.models.Reservation;
import com.restaurant.models.Table;
import com.restaurant.models.User;
import com.restaurant.repositories.jpa.ReservationJPARepository;
import com.restaurant.repositories.jpa.TableTypeJPARepository;
import com.restaurant.repositories.jpa.UserJPARepository;
import com.restaurant.utility.mappers.ReservationMapper;
import com.restaurant.views.ReservationView;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationJPARepository reservationJPARepository;
    private final UserJPARepository userJPARepository;
    private final TableTypeJPARepository tableTypeJPARepository;

    @Override
    public ResponseEntity<ReservationView> createReservationForUser(ReservationDTO reservationDTO, Long userId) {

        User user = userJPARepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found" + reservationDTO.getUsername()));
        Table table = tableTypeJPARepository.findById(reservationDTO.getTableId()).orElseThrow(() -> new RuntimeException("Table not found"));


        if (reservationJPARepository.existsByDateReservationBetweenAndTableEquals(reservationDTO.getDateReservation().minusHours(1), reservationDTO.getDateReservation().plusHours(1), table)) {
            //Check another LocalDataTime
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reservation reservation = Reservation.builder()
                .dateReservation(reservationDTO.getDateReservation())
                .customer(user)
                .table(table)
                .build();

        reservationJPARepository.save(reservation);
        return ResponseEntity.ok(ReservationMapper.mapReservationToReservationView(reservation));
    }

    @Override
    public ResponseEntity<ReservationView> getReservationById(Long reservationId) {
        return ResponseEntity.ok(reservationJPARepository.findById(reservationId).map(ReservationMapper::mapReservationToReservationView).orElseThrow(()->new RuntimeException("Reservation not found")));
    }

    @Override
    public Iterable<ReservationView> getAllUserReservations(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationView> updateReservation(Long reservationId, ReservationDTO reservationDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ReservationView> deleteReservation(Long reservationId) {
        return null;
    }
}
