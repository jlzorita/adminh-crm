package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import edu.uoc.tfg.crm.application.request.NotificacionRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Notificacion;
import edu.uoc.tfg.crm.domain.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificacionRepositoryImpl implements NotificacionRepository {

    private final SpringDataNotificacionRepository jpaRepository;
    @Override
    public List<Notificacion> buscaNotificacionesPorUsuario(String usuario) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        Notificacion n = Notificacion.builder().cliente(cliente).build();
        return jpaRepository.findAll(Example.of(NotificacionEntity.fromDomain(n))).stream().map(NotificacionEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean eliminarNotificacion(Long id) {
        if(!jpaRepository.getNotificacionById(id).isPresent()) return false;
        else{
            NotificacionEntity ne = new NotificacionEntity();
            ne.setId(id);
            jpaRepository.delete(ne);
            return true;
        }
    }

    @Override
    public Boolean crearNotificacion(NotificacionRequest notificacionRequest) {
        log.trace("crearNotificacion");
        Long clienteId = notificacionRequest.getClienteId();
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(clienteId);
        String mensaje = notificacionRequest.getMensaje();

        NotificacionEntity ne = new NotificacionEntity();
        ne.setMensaje(mensaje);
        ne.setLeido(false);
        ne.setCliente(cliente);
        jpaRepository.save(ne);
        return true;
    }

    @Override
    public Optional<NotificacionEntity> buscaNotificacion(Long id) {
        return jpaRepository.findById(id);
    }
}
