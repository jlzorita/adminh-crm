package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SpringDataNotificacionRepository extends JpaRepository<NotificacionEntity, Long> {
    @Query("from NotificacionEntity n where n.id = ?1")
    Optional<NotificacionEntity> getNotificacionById(Long id);
}
