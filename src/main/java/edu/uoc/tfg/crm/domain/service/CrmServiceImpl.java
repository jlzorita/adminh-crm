package edu.uoc.tfg.crm.domain.service;

import edu.uoc.tfg.crm.SesionData;
import edu.uoc.tfg.crm.application.request.MensajeRequest;
import edu.uoc.tfg.crm.application.request.NotificacionRequest;
import edu.uoc.tfg.crm.application.request.RespuestaRequest;
import edu.uoc.tfg.crm.application.request.UpdateClienteRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Notificacion;
import edu.uoc.tfg.crm.domain.repository.ClienteRepository;
import edu.uoc.tfg.crm.domain.repository.MensajeRepository;
import edu.uoc.tfg.crm.domain.repository.NotificacionRepository;
import edu.uoc.tfg.crm.infrastructure.repository.jpa.NotificacionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CrmServiceImpl implements CrmService {

    private final ClienteRepository clienteRepository;
    private final MensajeRepository mensajeRepository;
    private final NotificacionRepository notificacionRepository;

    @Override
    public void setSession(SesionData sesion) {
        Cliente.removeUsuario(sesion.getUsuario());
        if(sesion.isAlta())
            Cliente.addUsuario(sesion.getUsuario(),sesion.getSesion());
    }
    @Override
    public Optional<Cliente> buscaClientePorUsuario(String usuario) {
        return clienteRepository.buscaClientePorUsuario(usuario);
    }

    @Override
    public Optional<Cliente> buscaClientePorId(Long id) {
        return clienteRepository.buscaClientePorId(id);
    }

    @Override
    public List<Mensaje> buscaMensajesNoLeidosPorUsuario(String usuario) {
        return mensajeRepository.buscaMensajesNoLeidosPorCliente(usuario);
    }

    @Override
    public List<Mensaje> buscaMensajesContestadosPorUsuario(String usuario) {
        return mensajeRepository.buscaMensajesContestadosPorUsuario(usuario);
    }

    @Override
    public List<Cliente> buscaProveedores() {
        return clienteRepository.buscaProveedores();
    }

    @Override
    public Boolean crearNotificacion(NotificacionRequest notificacionRequest) {
        return notificacionRepository.crearNotificacion(notificacionRequest);
    }

    @Override
    public Optional<NotificacionEntity> buscaNotificacion(Long id) {
        return notificacionRepository.buscaNotificacion(id);
    }

    @Override
    public List<Mensaje> buscaMensajesNoLeidosPorComunidad(Long comunidad) {
        return mensajeRepository.buscaMensajesNoLeidosPorComunidad(comunidad);
    }

    @Override
    public Boolean eliminarNotificacion(Long id) {
        return notificacionRepository.eliminarNotificacion(id);

    }

    @Override
    public List<Notificacion> buscaNotificacionesPorUsuario(String usuario) {
        return notificacionRepository.buscaNotificacionesPorUsuario(usuario);
    }

    @Override
    public Boolean modificarDatosCliente(UpdateClienteRequest clienteRequest) {
        return clienteRepository.modificarDatosCliente(clienteRequest);
    }

    @Override
    public Boolean enviarMensaje(MensajeRequest mensajeRequest) {
        return mensajeRepository.enviarMensaje(mensajeRequest);
    }

    @Override
    public Boolean responderMensaje(RespuestaRequest respuestaRequest) {
        return mensajeRepository.responderMensaje(respuestaRequest);
    }



}