package edu.uoc.tfg.user.domain;

import lombok.*;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String cp;
    private String municipio;
    private String provincia;
    private String iban;
    private String email;
    private String usuario;

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
