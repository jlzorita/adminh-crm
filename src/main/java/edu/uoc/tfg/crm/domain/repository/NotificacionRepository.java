package edu.uoc.tfg.crm.domain.repository;

import edu.uoc.tfg.crm.application.request.NotificacionRequest;
import edu.uoc.tfg.crm.domain.Notificacion;
import edu.uoc.tfg.crm.infrastructure.repository.jpa.NotificacionEntity;

import java.util.List;
import java.util.Optional;

public interface NotificacionRepository {

    List<Notificacion> buscaNotificacionesPorUsuario(String usuario);

    Boolean eliminarNotificacion(Long id);

    Boolean crearNotificacion(NotificacionRequest notificacionRequest);

    Optional<NotificacionEntity> buscaNotificacion(Long id);
}
