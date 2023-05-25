package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import edu.uoc.tfg.crm.domain.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {
    @Query("from ClienteEntity c where c.usuario = ?1")
    Optional<ClienteEntity> findClienteByUsuario(String usuario);

    List<ClienteEntity> findALlByTipoEquals(Tipo tipo);

}
