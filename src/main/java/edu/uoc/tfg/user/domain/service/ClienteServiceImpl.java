package edu.uoc.tfg.user.domain.service;

import edu.uoc.tfg.user.ParSesion;
import edu.uoc.tfg.user.domain.Cliente;
import edu.uoc.tfg.user.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> findUsuario(String usuario) {
        return clienteRepository.findUsuario(usuario);
    }

    @Override
    public void addSession(ParSesion sesion) {
        Cliente.removeUsuario(sesion.getUsuario());
        Cliente.addUsuario(sesion.getUsuario(),sesion.getSesion());
    }
}