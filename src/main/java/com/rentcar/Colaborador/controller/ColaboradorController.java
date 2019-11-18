
//ColaboradorController completado
package com.rentcar.Colaborador.controller;


import com.rentcar.Colaborador.repository.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value =  "/cliente")
@CrossOrigin(value={})


public class ColaboradorController {
    @Autowired
    private ColaboradorRepository service;

    @GetMapping("/getAll")

    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.service.findAll());
    }

}
