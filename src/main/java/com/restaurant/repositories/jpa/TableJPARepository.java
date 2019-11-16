package com.restaurant.repositories.jpa;

import com.restaurant.models.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableJPARepository extends JpaRepository<Table, Long> {
}
