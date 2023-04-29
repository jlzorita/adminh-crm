package edu.uoc.tfg.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nombre;
    private String nif;
    private Tipo tipo;
    private String direccion;
    private String cp;
    private String municipio;
    private String provincia;
    private FormaPago formaPago;
    private String iban;
    private String email;
    private String telefono;
    private String usuario;

    @Builder.Default
    @JsonIgnore
    private Set<Mensaje> mensajes = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Notificacion> notificaciones = new HashSet<>();

    protected static Map<String,String> sesiones = new HashMap<>();

    public static String getSesion(String usuario){
        if(sesiones.containsKey(usuario))
            return sesiones.get(usuario);
        else return null;
    }

    public static void addUsuario(String usuario, String sesion){
        sesiones.put(usuario,sesion);
    }
    public static void removeUsuario(String usuario){
        if(sesiones.containsKey(usuario))
            sesiones.remove(usuario);
    }
}