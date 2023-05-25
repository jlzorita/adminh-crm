package edu.uoc.tfg.crm.infrastructure.repository.jpa;


import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Notificacion;
import lombok.*;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;

@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notificacion")
public class NotificacionEntity  implements DomainTranslatable<Notificacion> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "leido", nullable = false)
    private Boolean leido;

    @ManyToOne
    @JoinColumn(name="cliente_id", referencedColumnName = "id")
    private ClienteEntity cliente;


    public static NotificacionEntity fromDomain(Notificacion notificacion) {
        if (notificacion == null) {
            return null;
        }

        return NotificacionEntity.builder()
                .id(notificacion.getId())
                .mensaje(notificacion.getMensaje())
                .leido(notificacion.getLeido())
                .cliente(ClienteEntity.fromDomain(notificacion.getCliente()))
                .build();

    }
    @Override
    public Notificacion toDomain() {
        return Notificacion.builder()
                .id(this.getId())
                .mensaje(this.getMensaje())
                .leido(this.getLeido())
                //.cliente(this.getCliente().toDomain()) QUITADO PARA QUE NO SE VEA CLIENTE EN GET
                .build();
    }
}
