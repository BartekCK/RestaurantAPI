package com.restaurant.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

import static com.restaurant.utility.constants.ExceptionMessages.OPINION_SIZE_MESSAGE;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "opinions")
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long opinionId;

    @Column(nullable = false)
    private Date opinionDate;

    @OneToOne
    private User customer;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Size(max = 300, message = OPINION_SIZE_MESSAGE)
    private String textOpinion;

}
