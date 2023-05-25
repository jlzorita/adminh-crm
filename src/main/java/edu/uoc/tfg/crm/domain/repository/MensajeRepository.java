package edu.uoc.tfg.crm.domain.repository;

import edu.uoc.tfg.crm.application.request.MensajeRequest;
import edu.uoc.tfg.crm.application.request.RespuestaRequest;
import edu.uoc.tfg.crm.domain.Mensaje;

import java.util.List;

public interface MensajeRepository {
    List<Mensaje> buscaMensajesNoLeidosPorCliente(String cliente);

    Boolean enviarMensaje(MensajeRequest mensajeRequest);

    Boolean responderMensaje(RespuestaRequest respuestaRequest);

    List<Mensaje> buscaMensajesNoLeidosPorComunidad(Long comunidad);

    List<Mensaje> buscaMensajesContestadosPorUsuario(String usuario);
}
