
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
    public String saveColaborador(ColaboradorEntity colaborador) {

    }



    @GetMapping("/colaborador/¨{id}")
    public ResponseEntity<?> getColaboradorById(String id){
        ResponseEntity<?> response;
        try{
            ColaboradorEntity colaborador = this.service.findColaboradorById(id);
            response = new ResponseEntity<>(colaborador, HttpStatus.OK);
        }catch (Exception ex) {
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
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
                    response = new ResponseEntity<>("{\"Mensaje\":\"Cliente creado correctamente\"}", HttpStatus.CREATED);
                    break;
                case "NoRut":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Rut\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoEdad":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Edad debe estar entre 25 y 100\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoNombre":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Nombre\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoApellidoPaterno":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Apellido Paterno\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "noApellidoMaterno":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Apellido Materno\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoSexo":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Sexo\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "InvalidSexo":
                    response = new ResponseEntity<>("{\"Mensaje\":\"El sexo debe ser Masculino o Femenino\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoDireccion":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Direccion\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoTelefono":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Telefono\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "NoTipoLicencia":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta Tipo de licencia\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "invalidTipoLicencia":
                    response = new ResponseEntity<>("{\"Mensaje\":\"El tipo de licencia debe ser 'A', 'B' o 'C'\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "noFechaEmision":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta fecha emision licencia\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "noFechaVencimiento":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Falta fecha de vencimiento licencia\"}", HttpStatus.BAD_REQUEST);
                    break;
                case "invalidRut":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Ingrese un rut valido\"}", HttpStatus.CREATED);
                    break;
                case "invalidTelefono":
                    response = new ResponseEntity<>("{\"Mensaje\":\"Ingrese un telefono valido\"}", HttpStatus.CREATED);
                    break;
                default:
                    response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
                    break;
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



    @RequestMapping(value = "/colaborador/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizar(@RequestBody ColaboradorEntity colaborador, @PathVariable(value = "id") String id){
        ResponseEntity<?> response;
        String respuestaService = this.service.updateColaborador(colaborador,id);
        try {
            if(respuestaService.equals("update")){
                response = new ResponseEntity<>(colaborador, HttpStatus.OK);
            } else {
                response = new ResponseEntity<>("{\"Error\":\"Colaborador No existe\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex){
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}



