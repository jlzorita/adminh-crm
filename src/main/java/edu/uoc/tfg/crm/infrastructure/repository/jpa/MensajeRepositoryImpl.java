package edu.uoc.tfg.crm.infrastructure.repository.jpa;


import edu.uoc.tfg.crm.application.request.MensajeRequest;
import edu.uoc.tfg.crm.application.request.RespuestaRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.repository.ClienteRepository;
import edu.uoc.tfg.crm.domain.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MensajeRepositoryImpl implements MensajeRepository {
    private final SpringDataMensajeRepository jpaRepository;
    @Override
    public List<Mensaje> buscaMensajesNoLeidosPorCliente(String usuario) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        Mensaje m = Mensaje.builder().cliente(cliente).build();
        m.setLeidoM(false);
        return jpaRepository.findAll(Example.of(MensajeEntity.fromDomain(m))).stream().map(MensajeEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Mensaje> buscaMensajesContestadosPorUsuario(String usuario) {
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        Mensaje m = Mensaje.builder().cliente(cliente).build();
        m.setLeidoM(true);
        return jpaRepository.findAll(Example.of(MensajeEntity.fromDomain(m))).stream().map(MensajeEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Mensaje> buscaMensajesNoLeidosPorComunidad(Long comunidad) {
        Mensaje m = Mensaje.builder().comunidadId(comunidad).build();
        m.setLeidoM(false);
        return jpaRepository.findAll(Example.of(MensajeEntity.fromDomain(m))).stream().map(MensajeEntity::toDomain).collect(Collectors.toList());
    }

    @Override
    public Boolean enviarMensaje(MensajeRequest mensajeRequest) {
        log.trace("EnviarMensaje");
        Long id = mensajeRequest.getClienteId();
        String titulo = mensajeRequest.getTitulo();
        String mensaje = mensajeRequest.getMensaje();
        String administrador = mensajeRequest.getAdministrador();
        Long comunidadId = mensajeRequest.getComunidadId();

        if(jpaRepository.getMensajePendienteContestar(id).isPresent())
            return false;
        else{
            MensajeEntity me = new MensajeEntity();
            me.setTitulo(titulo);
            me.setMensaje(mensaje);
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(id);

            Date date = new Date();
            me.setFecham(date);
            me.setLeidom(false);
            me.setLeidor(false);
            me.setAdministrador(administrador);
            me.setComunidadId(comunidadId);
            me.setCliente(cliente);
            jpaRepository.save(me);
            return true;
        }
    }

    @Override
    public Boolean responderMensaje(RespuestaRequest respuestaRequest) {
        Long mensajeId = respuestaRequest.getMensajeId();
        String respuesta = respuestaRequest.getRespuesta();

        if(jpaRepository.getMensajeContestadoPorId(mensajeId).isPresent())
            return false;
        else{
            MensajeEntity me = jpaRepository.findById(mensajeId).get();
            me.setRespuesta(respuesta);
            me.setLeidom(true);
            Date date = new Date();
            me.setFechar(date);
            jpaRepository.save(me);
            return true;
        }
    }

}
