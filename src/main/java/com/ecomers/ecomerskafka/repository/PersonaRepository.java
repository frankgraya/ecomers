package com.ecomers.ecomerskafka.repository;

import com.ecomers.ecomerskafka.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}