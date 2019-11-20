package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;

import java.util.List;
import java.util.Optional;

public interface ColaboradorService {

    void saveColaborador (ColaboradorEntity Colaborador);
    List<ColaboradorEntity> findColaborador();
    void updateColaborador (ColaboradorEntity Colaborador, String id);
    Optional<ColaboradorEntity> findColaboradorById(String id);

}
