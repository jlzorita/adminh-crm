package edu.uoc.tfg.crm.infrastructure.repository.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.FormaPago;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Tipo;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Table(name = "cliente")
public class ClienteEntity implements DomainTranslatable<Cliente> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nif")
    private String nif;

    @Column(name = "tipo", nullable = false)
    private Tipo tipo;
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "cp", nullable = false)
    private String cp;

    @Column(name = "municipio", nullable = false)
    private String municipio;
    @Column(name = "provincia", nullable = false)
    private String provincia;

    @Column(name = "forma_pago")
    private FormaPago formaPago;
    @Column(name = "iban")
    private String iban;

    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "usuario")
    private String usuario;

    @OneToMany(mappedBy="usuario")
    private Set<MensajeEntity> mensajes = new HashSet<>();

    @OneToMany(mappedBy="usuario")
    private Set<NotificacionEntity> notificaciones = new HashSet<>();



    public static ClienteEntity fromDomain(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return ClienteEntity.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .nif(cliente.getNif())
                .tipo(cliente.getTipo())
                .direccion(cliente.getDireccion())
                .cp(cliente.getCp())
                .municipio(cliente.getMunicipio())
                .provincia(cliente.getProvincia())
                .formaPago(cliente.getFormaPago())
                .iban(cliente.getIban())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .usuario(cliente.getUsuario())
                .mensajes(cliente.getMensajes().stream().map(MensajeEntity::fromDomain).collect(Collectors.toSet()))
                .notificaciones(cliente.getNotificaciones().stream().map(NotificacionEntity::fromDomain).collect(Collectors.toSet()))
                .build();
    }
    @Override
    public Cliente toDomain() {
        return Cliente.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .nif(this.getNif())
                .tipo(this.getTipo())
                .direccion(this.getDireccion())
                .cp(this.getCp())
                .municipio(this.getMunicipio())
                .provincia(this.getProvincia())
                .formaPago(this.getFormaPago())
                .iban(this.getIban())
                .email(this.getEmail())
                .telefono(this.getTelefono())
                .usuario(this.getUsuario())
                .mensajes((this.getMensajes().stream().map(MensajeEntity::toDomain).collect(Collectors.toSet())))
                .notificaciones((this.getNotificaciones().stream().map(NotificacionEntity::toDomain).collect(Collectors.toSet())))
                .build();
    }
}