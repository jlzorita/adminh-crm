package edu.uoc.tfg.user.infrastructure.repository.jpa;

import edu.uoc.tfg.user.domain.Cliente;
import lombok.*;

import javax.persistence.*;

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

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "cp", nullable = false)
    private String cp;

    @Column(name = "municipio", nullable = false)
    private String municipio;
    @Column(name = "provincia", nullable = false)
    private String provincia;
    @Column(name = "iban")
    private String iban;

    @Column(name = "email")
    private String email;
    @Column(name = "usuario")
    private String usuario;

    public static ClienteEntity fromDomain(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return ClienteEntity.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .apellidos(cliente.getApellidos())
                .direccion(cliente.getDireccion())
                .cp(cliente.getCp())
                .municipio(cliente.getMunicipio())
                .provincia(cliente.getProvincia())
                .iban(cliente.getIban())
                .email(cliente.getEmail())
                .usuario(cliente.getUsuario())
                .build();
    }
    @Override
    public Cliente toDomain() {
        return Cliente.builder()
                .id(this.getId())
                .nombre(this.getNombre())
                .apellidos(this.getApellidos())
                .direccion(this.getDireccion())
                .cp(this.getCp())
                .municipio(this.getMunicipio())
                .provincia(this.getProvincia())
                .iban(this.getIban())
                .email(this.getEmail())
                .usuario(this.getUsuario())
                .build();
    }
}