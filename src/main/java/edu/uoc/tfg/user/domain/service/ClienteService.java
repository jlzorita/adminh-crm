package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.ParSesion;
import edu.uoc.tfg.user.domain.Cliente;

import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> findUsuario(String usuario);


    void addSession(ParSesion sesion);

}
