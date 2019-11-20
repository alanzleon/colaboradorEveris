package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpls implements ColaboradorService {

    @Autowired
    private ColaboradorService colaboradorservice;


    @Override
    public String saveColaborador(ColaboradorEntity Colaborador) {
        if (Colaborador.getEdad() >= 18) {
            this.colaboradorservice.save(Colaborador);
            return "Edad Correcta.";
        } else {
            return "Edad Incorrecta debe ser mayo o igual que 18";
        }
    }


    @Override
    public List<ColaboradorEntity> findColaborador() {
        return this.colaboradorservice.findAll();
    }

    @Override
    public String updateColaborador(ColaboradorEntity Colaborador, String id) {
        //Colaborador.setId(id);
        //this.colaboradorservice.saveColaborador(Colaborador);
        return "";
    }

    @Override
    public ColaboradorEntity findColaboradorById(@PathVariable String idConsultor) {
        return this.colaboradorservice.findById(idConsultor).get();
    }
}





