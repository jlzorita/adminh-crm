package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteRepositoryImpl implements ClienteRepository {

    private final SpringDataClienteRepository jpaRepository;


    @Override
    public Optional<Cliente> findUsuario(String nombre) {
        return jpaRepository.findUsuario(nombre).map(ClienteEntity::toDomain);
    }

}
