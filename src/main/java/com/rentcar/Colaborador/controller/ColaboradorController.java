
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



    @GetMapping("/colaborador/¨{id}")
    public ResponseEntity<?> getColaboradorById(String id){
        ResponseEntity<?> response;
        try{
            ColaboradorEntity colaborador = this.service.findColaboradorById(id);
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
                case "ok":
                    response = new ResponseEntity<>(mensaje ("Colaborador creado correctamente"), HttpStatus.CREATED);
                    break;
                case "EmptyRut":
                    response = new ResponseEntity<>(mensajeError("Falta Rut"), HttpStatus.BAD_REQUEST);
                    break;
                case "NoMayoredad":
                    response = new ResponseEntity<>(mensajeError("Edad debe estar entre 18 y 100"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyNombre":
                    response = new ResponseEntity<>(mensajeError("Falta Nombre"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyApellidoPaterno":
                    response = new ResponseEntity<>(mensajeError("Falta Apellido Paterno"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyApellidoMaterno":
                    response = new ResponseEntity<>(mensajeError("Falta Apellido Materno"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptySexo":
                    response = new ResponseEntity<>(mensajeError("Falta Sexo"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexo":
                    response = new ResponseEntity<>(mensajeError("El sexo debe ser Masculino o Femenino"), HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexEd":
                    response = new ResponseEntity<>(mensajeError("Edad Invalida para sexo: (“Femenino”menor de 60 años y mayor de 18 ) & (“Masculino” menor de 65 y mayor de 18) \u000B"), HttpStatus.BAD_REQUEST);
                    break;
                case "EmptyNivelPermiso":
                    response = new ResponseEntity<>(mensajeError("Falta Telefono"), HttpStatus.BAD_REQUEST);
                    break;
                case "invalidRut":
                    response = new ResponseEntity<>(mensajeError("Ingrese un rut valido"), HttpStatus.CREATED);
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
                    response = new ResponseEntity<>(mensajeError("El tipo de licencia debe ser 'A', 'B' o 'C'"),HttpStatus.BAD_REQUEST);
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

    public String mensaje(String msjPersonalizado) {
        return "{\"mensaje\":\""+msjPersonalizado+"\"}";
    }




}



