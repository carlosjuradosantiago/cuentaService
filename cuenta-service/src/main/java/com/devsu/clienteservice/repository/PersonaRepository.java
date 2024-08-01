package com.devsu.clienteservice.repository;

import com.devsu.clienteservice.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
