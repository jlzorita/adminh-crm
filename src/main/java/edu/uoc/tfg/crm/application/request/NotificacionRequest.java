package edu.uoc.tfg.crm.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class NotificacionRequest {
    @Getter
    private String mensaje;
    @Getter
    private Long clienteId;

    @JsonCreator
    public NotificacionRequest(@NotNull String mensaje, @NotNull Long clienteId) {
        this.mensaje = mensaje;
        this.clienteId = clienteId;
    }
}
