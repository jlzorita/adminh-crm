package edu.uoc.tfg.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.uoc.tfg.crm.SesionData;
import lombok.*;

import java.util.*;


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
    @JsonIgnore
    private String usuario;


    @Builder.Default
    @JsonIgnore
    private Set<Mensaje> mensajes = new HashSet<>();

    @Builder.Default
    @JsonIgnore
    private Set<Notificacion> notificaciones = new HashSet<>();


    protected static Map<String,String[]> sesiones = new HashMap<>();


    public static String[] getSesion(String usuario){
        if(sesiones.containsKey(usuario))
            return sesiones.get(usuario);
        else return null;
    }

    public static void setSesion(SesionData sesion) {
        removeUsuario(sesion.getUsuario());
        if(sesion.isAlta())
            addUsuario(sesion.getUsuario(),sesion.getSesion());
    }

    public static void addUsuario(String usuario, String value[]){
        sesiones.put(usuario, value);
    }
    public static void removeUsuario(String usuario){
        if(sesiones.containsKey(usuario))
            sesiones.remove(usuario);
    }
    public static String comprobarNivelUsuario(String sesion){

        String encontrado = null;
        Iterator<String> it = sesiones.keySet().iterator();

        while(it.hasNext()){
            String clave = it.next();
            String[] valor = sesiones.get(clave);
            if(valor[0].equals(sesion)) encontrado = valor[1];
        }

        return encontrado;
    }
}
