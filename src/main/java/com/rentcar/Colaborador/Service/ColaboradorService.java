package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;

import java.util.List;
import java.util.Optional;

public interface ColaboradorService {

    String saveColaborador(ColaboradorEntity Colaborador);
    List<ColaboradorEntity> findColaborador();
    String updateColaborador (ColaboradorEntity Colaborador, String id);
    ColaboradorEntity findColaboradorById(String id);
    String saveColaboradorF(ColaboradorEntity Colaborador);
    String saveColaboradorM(ColaboradorEntity Colaborador);
    String saveNivelPermiso(ColaboradorEntity Colaborador);


}
