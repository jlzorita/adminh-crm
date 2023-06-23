package edu.uoc.tfg.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    private Long id;
    private String mensaje;
    private Boolean leido;
    @JsonIgnore
    private Cliente cliente;

}