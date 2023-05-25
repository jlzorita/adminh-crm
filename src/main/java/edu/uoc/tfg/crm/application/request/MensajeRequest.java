package edu.uoc.tfg.crm.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.crm.domain.Mensaje;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class MensajeRequest{
    @Getter
    private String titulo;
    @Getter
    private String mensaje;
    @Getter
    private Long clienteId;
    @Getter
    private String administrador;
    @Getter
    private Long comunidadId;

    @JsonCreator
    public MensajeRequest(@NotNull String titulo, @NotNull String mensaje,@NotNull Long comunidadId,
                          @NotNull String administrador, @NotNull Long clienteId) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.clienteId = clienteId;
        this.administrador = administrador;
        this.comunidadId = comunidadId;
    }
}

