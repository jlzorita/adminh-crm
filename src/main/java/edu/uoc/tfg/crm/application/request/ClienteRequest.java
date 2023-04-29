package edu.uoc.tfg.crm.application.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.uoc.tfg.crm.domain.Cliente;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.NotNull;

public class ClienteRequest {

    @Getter
    @NotNull
    private final Cliente cliente;

    @JsonCreator
    public ClienteRequest(@JsonProperty("clienteData") @NotNull final Cliente cliente) {
        this.cliente = cliente;
    }
}