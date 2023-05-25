package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import edu.uoc.tfg.crm.application.request.UpdateClienteRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Tipo;
import edu.uoc.tfg.crm.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteRepositoryImpl implements ClienteRepository {

    private final SpringDataClienteRepository jpaRepository;



    @Override
    public Optional<Cliente> buscaClientePorUsuario(String usuario) {
        return jpaRepository.findClienteByUsuario(usuario).map(ClienteEntity::toDomain);
    }

    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        return jpaRepository.findById(id).map(ClienteEntity::toDomain);
    }

    @Override
    public Boolean modificarDatosCliente(UpdateClienteRequest clienteRequest) {
        log.trace("updateCliente");

        Long id = clienteRequest.getId();
        String direccion = clienteRequest.getDireccion();
        String cp = clienteRequest.getCp();
        String municipio = clienteRequest.getMunicipio();
        String provincia = clienteRequest.getProvincia();
        String telefono = clienteRequest.getTelefono();
        String email = clienteRequest.getEmail();

        ClienteEntity cliente = jpaRepository.findById(id).get();
        if(cliente == null) return false;
        else {
            if(direccion != null) cliente.setDireccion(direccion);
            if(cp != null) cliente.setCp(cp);
            if(municipio != null) cliente.setMunicipio(municipio);
            if(provincia != null) cliente.setProvincia(provincia);
            if(telefono != null) cliente.setTelefono(telefono);
            if(email != null) cliente.setEmail(email);

            jpaRepository.save(cliente);
            return true;
        }
    }

    @Override
    public List<Cliente> buscaProveedores() {
        return jpaRepository.findALlByTipoEquals(Tipo.PROVEEDOR).stream().map(ClienteEntity::toDomain).collect(Collectors.toList());
    }
}
