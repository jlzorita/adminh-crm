package edu.uoc.tfg.crm.application.rest;

import edu.uoc.tfg.crm.application.request.MensajeRequest;
import edu.uoc.tfg.crm.application.request.NotificacionRequest;
import edu.uoc.tfg.crm.application.request.RespuestaRequest;
import edu.uoc.tfg.crm.application.request.UpdateClienteRequest;
import edu.uoc.tfg.crm.domain.Cliente;
import edu.uoc.tfg.crm.domain.Mensaje;
import edu.uoc.tfg.crm.domain.Notificacion;
import edu.uoc.tfg.crm.domain.Tipo;
import edu.uoc.tfg.crm.domain.service.CrmService;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin
@RestController
public class ClienteRESTController {

    private final CrmService crmService;

    @GetMapping("/user/sesion/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public String[] getClienteSesion(@PathVariable String usuario) {
        return Cliente.getSesion(usuario);
    }

    @GetMapping("/usuario/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> getClienteByUsuario(@PathVariable String usuario, @RequestParam String sesion) {
        //Usuario tiene que ser el mismo
        if(!Cliente.getSesion(usuario)[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else {
            return crmService.buscaClientePorUsuario(usuario).map(cliente -> ResponseEntity.ok().body(cliente))
                    .orElse(ResponseEntity.notFound().build());
        }
    }

    @PutMapping("/usuario/actualizar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity actualizarUsuario(@RequestBody UpdateClienteRequest clienteRequest, @RequestParam String sesion) {
        Optional<Cliente> cliente = crmService.buscaClientePorId(clienteRequest.getId());
        //Usuario tiene que ser el mismo
        if(!Cliente.getSesion(cliente.get().getUsuario())[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(crmService.modificarDatosCliente(clienteRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/mensaje")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity enviarMensaje(@RequestBody MensajeRequest mensajeRequest, @RequestParam String sesion) {
        Optional<Cliente> cliente = crmService.buscaClientePorId(mensajeRequest.getClienteId());
        //Usuario tiene que ser el mismo
        if(!Cliente.getSesion(cliente.get().getUsuario())[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(crmService.enviarMensaje(mensajeRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/notificacion/eliminar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity eliminarNotificacion(@PathVariable Long id,  @RequestParam String sesion) {
        Long clienteId = crmService.buscaNotificacion(id).get().getCliente().getId();
        //Usuario tiene que ser el mismo
        Optional<Cliente> cliente = crmService.buscaClientePorId(clienteId);
        if(!Cliente.getSesion(cliente.get().getUsuario())[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(crmService.eliminarNotificacion(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/respuesta")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity responderMensaje(@RequestBody RespuestaRequest respuestaRequest, @RequestParam String sesion) {
        //Solo puede responder usuario con rango administrador
        if(Cliente.comprobarNivelUsuario(sesion) == null || !Cliente.comprobarNivelUsuario(sesion).equals("2"))return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(crmService.responderMensaje(respuestaRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/mensaje/usuario/{usuario}") //Get Mensajes no respondidos por cliente
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Mensaje>> getMensajesByUsuario(@PathVariable String usuario, @RequestParam String tipo, @RequestParam String sesion) {
        //Usuario tiene que ser el mismo
        if(!Cliente.getSesion(usuario)[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(tipo.equals("NOLEIDO")) {
            return ResponseEntity.ok().body(crmService.buscaMensajesNoLeidosPorUsuario(usuario));
        }else if(tipo.equals("CONTESTADOS")) {
            return ResponseEntity.ok().body(crmService.buscaMensajesContestadosPorUsuario(usuario));
        }else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mensaje/comunidad/{comunidad}") //Get Mensajes no respondidos por comunidad
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Mensaje>> getMensajesByComunidad(@PathVariable Long comunidad, @RequestParam String sesion) {
        //Solo puede ver todos los mensajes de una comunidad usuario con rango administrador
        if(Cliente.comprobarNivelUsuario(sesion) == null || !Cliente.comprobarNivelUsuario(sesion).equals("2")) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok().body(crmService.buscaMensajesNoLeidosPorComunidad(comunidad));
    }

    @GetMapping("/cliente/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id, @RequestParam String sesion) {
        //Si el cliente es un proveedor cualquiera usuario logeado puede obtener los datos
        //Si el cliente es un vecino solo su usuario puede obtener sus datos
        Optional<Cliente> c = crmService.buscaClientePorId(id);
        if(c.get().getTipo().equals(Tipo.PROVEEDOR))
            return crmService.buscaClientePorId(id).map(cliente -> ResponseEntity.ok().body(cliente))
                .orElse(ResponseEntity.notFound().build());
        else if(!Cliente.getSesion(c.get().getUsuario())[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else return crmService.buscaClientePorId(id).map(cliente -> ResponseEntity.ok().body(cliente))
                        .orElse(ResponseEntity.notFound().build());
        }

    @GetMapping("/proveedores/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Cliente>> getProveedores(@RequestParam String sesion) {
        //Cualquier usuario logeado puede ver la lista de proveedores
        if(Cliente.comprobarNivelUsuario(sesion).equals(null)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        else return ResponseEntity.ok().body(crmService.buscaProveedores());
    }

    @GetMapping("/notificacion/{usuario}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Notificacion>> getNotificacionesByCliente(@PathVariable String usuario, @RequestParam String sesion) {
        //Solo el usuario de un cliente puede ver sus notificaciones
        if(!Cliente.getSesion(usuario)[0].equals(sesion)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok().body(crmService.buscaNotificacionesPorUsuario(usuario));
    }

    @PostMapping("/notificacion/crear/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity crearNotificacion(@RequestBody NotificacionRequest notificacionRequest, @RequestParam String sesion) {
        //Solo un administrador puede crear notificaciones
        if(Cliente.comprobarNivelUsuario(sesion)== null || !Cliente.comprobarNivelUsuario(sesion).equals("2"))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        if(crmService.crearNotificacion(notificacionRequest)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}