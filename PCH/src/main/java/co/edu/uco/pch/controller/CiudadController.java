package co.edu.uco.pch.controller;

import co.edu.uco.pch.business.facade.impl.ciudad.ConsultarCiudadesFacade;
import co.edu.uco.pch.business.facade.impl.ciudad.RegistrarCiudadFacade;
import co.edu.uco.pch.controller.response.CiudadResponse;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.dto.CiudadDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ciudades")
public final class CiudadController {

    @GetMapping("/dummy")
    public CiudadDTO dummy(){
        return CiudadDTO.build();
    }

    @GetMapping("/p")
    public ResponseEntity<CiudadResponse> consultar(){
    	
        var httpStatusCode = HttpStatus.ACCEPTED;
        var ciudadResponse = new CiudadResponse();

        try {

            var ciudadesDto = CiudadDTO.build();
            var facade = new ConsultarCiudadesFacade();
            
            var ciudadesRetorno=new ArrayList<CiudadDTO>();
            ciudadesRetorno.add(CiudadDTO.build());
            ciudadesRetorno.add(CiudadDTO.build());
            ciudadesRetorno.add(CiudadDTO.build());
            ciudadesRetorno.add(CiudadDTO.build());
            ciudadResponse.setDatos(facade.execute(ciudadesDto));
            ciudadResponse.getMensajes().add("Ciudades consultadas exitosamente");
        }catch (final PCHException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de consultar las ciudades";
            ciudadResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();

        }

        return new ResponseEntity<>(ciudadResponse, httpStatusCode);
    }

    @PostMapping
    public ResponseEntity<CiudadResponse> crear(@RequestBody CiudadDTO ciudad){
        var httpStatusCode = HttpStatus.ACCEPTED;
        var ciudadResponse = new CiudadResponse();

        try {

            var facade = new RegistrarCiudadFacade();

            facade.execute(ciudad);
            ciudadResponse.getMensajes().add("Ciudades creada exitosamente");
        }catch (final PCHException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            
            ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            
            var mensajeUsuario = "Se ha presntado un problema tratando de registrar la nueva ciudad";
            ciudadResponse.getMensajes().add(mensajeUsuario);
            exception.printStackTrace();

        }

        return new ResponseEntity<>(ciudadResponse, httpStatusCode);
    }

@DeleteMapping("/{id}")
    public ResponseEntity<CiudadResponse> eliminar(@PathVariable UUID id){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var ciudadResponse = new CiudadResponse();

        try {

            //var facade = new EliminarCiudadFacade();

            //facade.execute(id);
            ciudadResponse.getMensajes().add("Ciudades eliminada exitosamente");
        }catch (final PCHException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de eliminar la ciudad";
            ciudadResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();

        }

        return new ResponseEntity<>(ciudadResponse, httpStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CiudadResponse> modificar(@PathVariable UUID id, @RequestBody CiudadDTO ciudadDTO){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var ciudadResponse = new CiudadResponse();

        try {
            ciudadDTO.setId(id);
            //var facade = new ModificarCiudadFacade();

            //facade.execute(id);
            ciudadResponse.getMensajes().add("Ciudades modificada exitosamente");
        }catch (final PCHException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            ciudadResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();
        }catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = "Se ha presntado un problema tratando de modificar la ciudad";
            ciudadResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();

        }

        return new ResponseEntity<>(ciudadResponse, httpStatusCode);
    }
}