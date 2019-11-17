package com.restaurant.repositories.jpa;

import com.restaurant.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJPARepository extends JpaRepository<Employee, Long> {

    boolean existsByUserId(Long userId);

    void deleteByUserId(Long userId);
}
