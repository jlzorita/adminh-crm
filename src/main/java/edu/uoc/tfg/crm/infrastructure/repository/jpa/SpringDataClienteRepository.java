package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("from ClienteEntity u where u.usuario = ?1")
    Optional<ClienteEntity> findUsuario(String usuario);
}
