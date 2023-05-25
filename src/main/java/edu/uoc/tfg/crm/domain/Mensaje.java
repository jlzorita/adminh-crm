package edu.uoc.tfg.crm.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fechaM;
    private Boolean leidoM;
    private String respuesta;
    private Date fechaR;
    private Boolean leidoR;
    private String administrador;
    private Long comunidadId;
    private Cliente cliente;
}
