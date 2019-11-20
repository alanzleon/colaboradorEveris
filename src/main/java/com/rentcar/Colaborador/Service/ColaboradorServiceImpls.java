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
    private ColaboradorService colaboradorService;


    @Override
    public void saveColaborador(ColaboradorEntity Colaborador) {
        if (Colaborador.getEdad>=18){
            this.repository.save(Colaborador);
        }
        else{}

    }

    @Override
    public List<ColaboradorEntity> findColaborador() {
        return this.repository.findAll();
    }

    @Override
    public void updateColaborador(ColaboradorEntity Colaborador, String id) {
        Colaborador.setId(id);
        this.repository.save(Colaborador);
    }

    @Override
    public Optional<ColaboradorEntity> findColaboradorById(@PathVariable String idConsultor) {
        return this.repository.findById(idConsultor);
    }
}





