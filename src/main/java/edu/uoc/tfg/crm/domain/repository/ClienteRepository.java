package edu.uoc.tfg.crm.domain.repository;

import edu.uoc.tfg.crm.application.request.UpdateClienteRequest;
import edu.uoc.tfg.crm.domain.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Optional<Cliente> buscaClientePorUsuario(String usuario);

    Optional<Cliente> buscaClientePorId(Long id);

    Boolean modificarDatosCliente(UpdateClienteRequest clienteRequest);

    List<Cliente> buscaProveedores();
}