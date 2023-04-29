package edu.uoc.tfg.crm.domain.repository;

import edu.uoc.tfg.crm.domain.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findUsuario(String usuario);
}