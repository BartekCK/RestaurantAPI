package com.restaurant.repositories.jpa;

import com.restaurant.repositories.OpinionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OpinionRepositoryImpl implements OpinionRepository {

    private final OpinionJPARepository opinionJPARepository;
}
