package edu.uoc.tfg.crm.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class UpdateClienteRequest {
    @Getter
    private Long id;
    @Getter
    private String direccion;
    @Getter
    private String cp;
    @Getter
    private String municipio;
    @Getter
    private String provincia;
    @Getter
    private String telefono;
    @Getter
    private String email;
    @JsonCreator
    public UpdateClienteRequest(Long id, String direccion, String cp, String municipio, String provincia, String telefono, String email) {
        this.id = id;
        this.direccion = direccion;
        this.cp = cp;
        this.municipio = municipio;
        this.provincia = provincia;
        this.telefono = telefono;
        this.email = email;
    }
}