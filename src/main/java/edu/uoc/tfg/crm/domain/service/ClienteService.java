package edu.uoc.tfg.crm.domain.service;

import edu.uoc.tfg.crm.ParSesion;
import edu.uoc.tfg.crm.domain.Cliente;

import java.util.Optional;

public interface ClienteService {
    Optional<Cliente> findUsuario(String usuario);


    void addSession(ParSesion sesion);

}
