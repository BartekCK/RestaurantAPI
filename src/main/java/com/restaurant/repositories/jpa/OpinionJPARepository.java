package com.restaurant.repositories.jpa;

import com.restaurant.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionJPARepository extends JpaRepository<Opinion, Long> {
}
