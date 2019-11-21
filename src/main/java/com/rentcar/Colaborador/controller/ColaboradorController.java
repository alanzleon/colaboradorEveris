
//ColaboradorController completado
package com.rentcar.Colaborador.controller;


        import com.rentcar.Colaborador.Service.ColaboradorServiceImpls;
        import com.rentcar.Colaborador.entity.ColaboradorEntity;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;



        import java.util.List;


@RestController
@RequestMapping(value =  "/colaborador")
@CrossOrigin(value={})

public class ColaboradorController {

    @Autowired
    private ColaboradorServiceImpls service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        ResponseEntity<?> response;
        try {
            List<ColaboradorEntity> colaboradorEntityList = this.service.findColaborador();
            response = new ResponseEntity<>(colaboradorEntityList, HttpStatus.OK);
        }catch(Exception ex){
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo esta mal :/ \"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getColaboradorById(String id){
        ResponseEntity<?> response;
        try {
            ColaboradorEntity colaborador = this.service.findColaboradorById(id);
            response = new ResponseEntity<>(colaborador, HttpStatus.OK);
        }catch(Exception ex){
            System.out.println(ex);
            response = new ResponseEntity<>("{\"Error\":\"Algo esta mal :/ \"}"+ ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    @PostMapping("/addColaborador")
    public ResponseEntity<?> addColaborador (@RequestBody ColaboradorEntity colaborador){
        ResponseEntity<?> response;
        String respuestaService = this.service.saveColaborador(colaborador);
        try{
            if(respuestaService.equals("EdadCorrecta.")) {
                response = new ResponseEntity<>("{\"Mensaje\":\"Colaborador creado correctamente\"}", HttpStatus.CREATED);
            } else {
                response = new ResponseEntity<>("{\"Error\":\"Edad debe ser mayor a 18 años\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @PostMapping("/addColaboradorF")
    public ResponseEntity<?> addColaboradorF (@RequestBody ColaboradorEntity colaborador){
        ResponseEntity<?> response;
        String respuestaService = this.service.saveColaboradorF(colaborador);
        try{
            if(respuestaService.equals("ColaboradoraCorrecta.")) {
                response = new ResponseEntity<>("{\"Mensaje\":\"Colaboradora creada correctamente\"}", HttpStatus.CREATED);
            } else if (respuestaService.equals("erroredad")){
                response = new ResponseEntity<>("{\"Error\":\"La Edad debe ser mayor a 18 y menor de 65'\"}",HttpStatus.BAD_REQUEST);
            }else{
                respuestaService.equals("errorsexo");
                response = new ResponseEntity<>("{\"Error\":\"El sexo no es Femenino'\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


    @PostMapping("/addColaboradorM")
    public ResponseEntity<?> addColaboradorM (@RequestBody ColaboradorEntity colaborador){
        ResponseEntity<?> response;
        String respuestaService = this.service.saveColaboradorM(colaborador);
        try{
            if(respuestaService.equals("ColaboradorCorrecto.")) {
                response = new ResponseEntity<>("{\"Mensaje\":\"Colaborador creado correctamente\"}", HttpStatus.CREATED);
            } else if (respuestaService.equals("erroredad")){
                response = new ResponseEntity<>("{\"Error\":\"La Edad debe ser mayor a 18 y menor de 60'\"}",HttpStatus.BAD_REQUEST);
            }else{
                respuestaService.equals("errorsexo");
                response = new ResponseEntity<>("{\"Error\":\"El sexo no es Masculino'\"}",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }



    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> actualizar(@RequestBody ColaboradorEntity colaborador, @PathVariable(value = "id") String id){
        ResponseEntity<?> response;
        String respuestaService = this.service.updateColaborador(colaborador, id);
        response = new ResponseEntity<>(respuestaService, HttpStatus.OK);
        return response;
    }


}
