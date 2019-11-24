
//ColaboradorController completado
package com.rentcar.Colaborador.controller;


        import com.rentcar.Colaborador.Service.ColaboradorServiceImpls;
        import com.rentcar.Colaborador.entity.ColaboradorEntity;
        import com.rentcar.Colaborador.repository.ColaboradorRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;


        import java.util.Arrays;
        import java.util.List;
        import java.util.regex.Pattern;


@RestController
@RequestMapping(value =  "/colaborador")
@CrossOrigin(value={})

public class ColaboradorController {

    @Autowired
    private ColaboradorServiceImpls service;

    @GetMapping("/colaborador")
    public ResponseEntity<?> getAll(){
        ResponseEntity<?> response;
        try {
            List<ColaboradorEntity> colaborador = this.service.findColaborador();
            response = new ResponseEntity<>(colaborador, HttpStatus.OK);
        } catch (Exception ex) {
            response = new ResponseEntity<>(mensajeError("Algo salio mal"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }



    @GetMapping("/colaborador/{rut}")
    public ResponseEntity<?> getColaboradorByRut(@PathVariable(value = "rut") String rut){
        ResponseEntity<?> response;
        try{
            ColaboradorEntity colaborador = this.service.findColaboradorByRut(rut);
            response = new ResponseEntity<>(colaborador, HttpStatus.OK);
        }catch (Exception ex) {
            System.out.println(ex);
            response = new ResponseEntity<>(mensajeError("Algo salio mal")+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }







    @PostMapping("/colaborador")
    public ResponseEntity<?> addCliente (@RequestBody ColaboradorEntity colaborador){
        ResponseEntity<?> response;
        String respuestaService = this.service.saveColaborador(colaborador);
        try{
            switch (respuestaService) {
                case "EmptyNombre":
                    response = new ResponseEntity<>(mensajeError("Falta nombre"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyApellidoPaterno":
                    response = new ResponseEntity<>(mensajeError("Falta apellidoPaterno"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyApellidoMaterno":
                    response = new ResponseEntity<>(mensajeError("Falta apellidoMaterno"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyRut":
                    response = new ResponseEntity<>(mensajeError("Falta rut"), HttpStatus.BAD_REQUEST);
                    break;
                case "invalidRut":
                    response = new ResponseEntity<>(mensajeError("Ingrese un rut valido, respetando '.' y '-' "), HttpStatus.CREATED);
                    break;
                case "Emptyedad":
                    response = new ResponseEntity<>(mensajeError("falta edad"), HttpStatus.BAD_REQUEST);
                    break;
                case "NoMayoredad":
                    response = new ResponseEntity<>(mensajeError("Edad debe estar entre 18 y 100"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptySexo":
                    response = new ResponseEntity<>(mensajeError("Falta sexo"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexo":
                    response = new ResponseEntity<>(mensajeError("El sexo debe ser Masculino o Femenino"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexoM":
                    response = new ResponseEntity<>(mensajeError("El sexo Masculino deve tener la Edad  entre 18 y 65"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexoF":
                    response = new ResponseEntity<>(mensajeError("El sexo Femenino deve tener la Edad  entre 18 y 60"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyNivelPermisoM":
                    response = new ResponseEntity<>(mensajeError("Falta nivelpermiso (M)"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidobtenerPermisoM":
                    response = new ResponseEntity<>(mensajeError("El Nivel de Permiso Masculino debe ser 'Administrado', 'Supervisor' o 'Vendedor'"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyNivelPermisoF":
                    response = new ResponseEntity<>(mensajeError("Falta nivelpermiso (F)"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidobtenerPermisoF":
                    response = new ResponseEntity<>(mensajeError("El Nivel de Permiso Femenino debe ser 'Administrado', 'Supervisor' o 'Vendedor'"), HttpStatus.BAD_REQUEST);
                    break;
                case "ok":
                    response = new ResponseEntity<>(mensajeCreado("Colaborador creado correctamente"), HttpStatus.CREATED);
                    break;
                default:
                    response = new ResponseEntity<>(mensajeError("Algo salio mal"),HttpStatus.INTERNAL_SERVER_ERROR);
                    break;
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>(mensajeError("Algo salio mal"),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }




    @RequestMapping(value = "/colaborador/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizar(@RequestBody ColaboradorEntity colaborador, @PathVariable(value = "id") String id){
        ResponseEntity<?> response;
        String respuestaService = this.service.updateColaborador(colaborador,id);
        try {
            switch(respuestaService) {
                case "update":
                    response = new ResponseEntity<>(colaborador, HttpStatus.OK);
                    break;
                case "notfound":
                    response = new ResponseEntity<>(mensajeError("Cliente No existe"),HttpStatus.NOT_FOUND);
                    break;
                case "invalidEdad":
                    response = new ResponseEntity<>(mensajeError("La edad debe estar entre 18 y 100 años"),HttpStatus.BAD_REQUEST);
                    break;
                case "invalidSexo":
                    response = new ResponseEntity<>(mensajeError("El sexo debe ser Masculino o Femenino"),HttpStatus.BAD_REQUEST);
                    break;
                case "invalidTelefono":
                    response = new ResponseEntity<>(mensajeError("Ingrese un telefono valido"),HttpStatus.BAD_REQUEST);
                    break;
                case "invalidTipoLicencia":
                    response = new ResponseEntity<>(mensajeError("El Nivel de Permiso debe ser 'Vendedor', 'Administrador' o 'Supervisor'"),HttpStatus.BAD_REQUEST);
                    break;
                default:
                    response = new ResponseEntity<>(mensajeError("Algo salio mal"),HttpStatus.INTERNAL_SERVER_ERROR);
                    break;
            }
        } catch (Exception ex){
            response = new ResponseEntity<>(mensajeError(ex.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    public String mensajeError(String msjPersonalizado) {
        return "{\"Error\":\""+msjPersonalizado+"\"}";
    }

    public String mensajeCreado(String msjPersonalizado) {
        return "{\"Creado\":\""+msjPersonalizado+"\"}";
    }



    public String mensaje(String msjPersonalizado) {
        return "{\"mensaje\":\""+msjPersonalizado+"\"}";
    }




}



