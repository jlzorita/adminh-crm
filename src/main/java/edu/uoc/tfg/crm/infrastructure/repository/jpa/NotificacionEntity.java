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
    @JoinColumn(name="usuario", referencedColumnName = "usuario")
    private ClienteEntity usuario;


    public static NotificacionEntity fromDomain(Notificacion notificacion) {
        if (notificacion == null) {
            return null;
        }

        return NotificacionEntity.builder()
                .id(notificacion.getId())
                .mensaje(notificacion.getMensaje())
                .leido(notificacion.getLeido())
                .usuario(ClienteEntity.fromDomain(notificacion.getUsuario()))
                .build();

    }
    @Override
    public Notificacion toDomain() {
        return Notificacion.builder()
                .id(this.getId())
                .mensaje(this.getMensaje())
                .leido(this.getLeido())
                .usuario(this.getUsuario().toDomain())
                .build();
    }
}
