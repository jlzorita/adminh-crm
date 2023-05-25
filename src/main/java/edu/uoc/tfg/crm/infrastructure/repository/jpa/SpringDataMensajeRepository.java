package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import edu.uoc.tfg.crm.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface SpringDataMensajeRepository extends JpaRepository<MensajeEntity, Long> {

    @Query("from MensajeEntity m where m.leidom = false and m.cliente.id = ?1")
    Optional<MensajeEntity> getMensajePendienteContestar(Long clienteId);

    @Query("from MensajeEntity m where m.leidom = true and m.id = ?1")
    Optional<MensajeEntity> getMensajeContestadoPorId(Long id);

}
