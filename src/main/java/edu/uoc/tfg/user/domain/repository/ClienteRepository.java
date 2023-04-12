package edu.uoc.tfg.user.domain.repository;

import edu.uoc.tfg.user.domain.Cliente;

import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> findUsuario(String usuario);
}