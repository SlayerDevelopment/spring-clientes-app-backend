package com.slayer.api.main.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slayer.api.main.entities.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, Long> {

}
