package edu.uoc.tfg.crm.domain;

import lombok.*;

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
    private Cliente usuario;

}