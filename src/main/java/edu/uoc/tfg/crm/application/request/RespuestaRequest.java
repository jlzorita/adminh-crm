package edu.uoc.tfg.crm.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.crm.domain.Mensaje;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RespuestaRequest{
    @Getter
    private String respuesta;
    @Getter
    private Long mensajeId;

    @JsonCreator
    public RespuestaRequest(@NotNull String respuesta, @NotNull Long mensajeId) {
        this.respuesta = respuesta;
        this.mensajeId = mensajeId;
    }
}
