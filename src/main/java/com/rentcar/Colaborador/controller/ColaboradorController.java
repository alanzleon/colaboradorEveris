
//ColaboradorController completado
package com.rentcar.Colaborador.controller;


import com.rentcar.Colaborador.entity.ColaboradorEntity;
import com.rentcar.Colaborador.repository.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value =  "/colaborador")
@CrossOrigin(value={})
public class ColaboradorController {

    @Autowired
    private ColaboradorRepository service;

    @GetMapping("/getAll")

    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getClienteById(String id){
        return ResponseEntity.ok(this.service.findClienteById(id));
    }


    @PostMapping("/addColaborador")
    public ColaboradorEntity addColaborador (@RequestBody ColaboradorEntity Colaborador ){
        this.service.saveColaborador(Colaborador);
        return Colaborador;
    }

    @RequestMapping(value = "/actualizar/{id}", method = RequestMethod.PUT)
    public  ColaboradorEntity actualizar(@RequestBody ColaboradorEntity Colaborador, @PathVariable(value = "id") String id){
        this.service.updateColaborador(Colaborador,id);
        return Colaborador;
    }


}
