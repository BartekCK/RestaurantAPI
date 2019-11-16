package com.restaurant.services;

import com.restaurant.repositories.OpinionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OpinionServiceImpl {
    private final OpinionRepository opinionRepository;
}
