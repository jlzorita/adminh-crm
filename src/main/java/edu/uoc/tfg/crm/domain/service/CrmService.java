package edu.uoc.tfg.crm.domain.service;

import edu.uoc.tfg.crm.SesionData;
import edu.uoc.tfg.crm.application.request.MensajeRequest;
import edu.uoc.tfg.crm.application.request.NotificacionRequest;
import edu.uoc.tfg.crm.application.request.RespuestaRequest;
import edu.uoc.tfg.crm.application.request.UpdateClienteRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Notificacion;
import edu.uoc.tfg.crm.infrastructure.repository.jpa.NotificacionEntity;

import java.util.List;
import java.util.Optional;

public interface CrmService {

    //void setSession(SesionData sesion);

    Boolean modificarDatosCliente(UpdateClienteRequest clienteRequest);
    Boolean enviarMensaje(MensajeRequest mensajeRequest);
    Boolean responderMensaje(RespuestaRequest respuestaRequest);
    Boolean eliminarNotificacion(Long id);
    Boolean crearNotificacion(NotificacionRequest notificacionRequest);
    Optional<Cliente> buscaClientePorId(Long id);
    Optional<Cliente> buscaClientePorUsuario(String usuario);
    List<Mensaje> buscaMensajesNoLeidosPorUsuario(String usuario);
    List<Notificacion> buscaNotificacionesPorUsuario(String usuario);
    List<Mensaje> buscaMensajesNoLeidosPorComunidad(Long comunidad);
    List<Cliente> buscaProveedores();
    Optional<NotificacionEntity> buscaNotificacion(Long id);

    List<Mensaje> buscaMensajesContestadosPorUsuario(String usuario);






}
