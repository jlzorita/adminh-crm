package edu.uoc.tfg.crm.infrastructure.repository.jpa;


import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import lombok.*;
import javax.persistence.*;
import javax.persistence.Embeddable;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensaje")
public class MensajeEntity implements DomainTranslatable<Mensaje> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @Column(name = "fecham", nullable = false)
    private Date fecham;

    @Column(name = "leidom", nullable = false)
    private Boolean leidom;

    @Column(name = "respuesta")
    private String respuesta;

    @Column(name = "fechar")
    private Date fechar;

    @Column(name = "leidor", nullable = false)
    private Boolean leidor;

    @ManyToOne
    @JoinColumn(name="usuario", referencedColumnName = "usuario")
    private ClienteEntity usuario;

    @Column(name = "administrador", nullable = false)
    private String administrador;
    @Column(name = "comunidad_id", nullable = false)
    private Long comunidadId;

    public static MensajeEntity fromDomain(Mensaje mensaje) {
        if (mensaje == null) {
            return null;
        }

        return MensajeEntity.builder()
                .id(mensaje.getId())
                .titulo(mensaje.getTitulo())
                .mensaje(mensaje.getMensaje())
                .fecham(mensaje.getFechaM())
                .leidom(mensaje.getLeidoM())
                .respuesta(mensaje.getRespuesta())
                .fechar(mensaje.getFechaR())
                .leidor(mensaje.getLeidoR())
                .usuario(ClienteEntity.fromDomain(mensaje.getUsuario()))
                .administrador(mensaje.getAdministrador())
                .comunidadId(mensaje.getComunidadId())
                .build();

    }
    @Override
    public Mensaje toDomain() {
        return Mensaje.builder()
                .id(this.getId())
                .titulo(this.getTitulo())
                .mensaje(this.getMensaje())
                .fechaM(this.getFecham())
                .leidoM(this.getLeidom())
                .respuesta(this.getRespuesta())
                .fechaR(this.getFechar())
                .leidoR(this.getLeidor())
                .usuario(this.getUsuario().toDomain())
                .administrador(this.getAdministrador())
                .comunidadId(this.getComunidadId())
                .build();
    }
}
