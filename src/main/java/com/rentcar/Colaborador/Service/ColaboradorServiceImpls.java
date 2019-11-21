package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import com.rentcar.Colaborador.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorServiceImpls implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepositoryl;


    @Override
    public String saveColaborador(ColaboradorEntity Colaborador) {
        if (Colaborador.getEdad() >= 18) {
            this.colaboradorRepositoryl.save(Colaborador);
            return "EdadCorrecta.";
        } else {
            return "Edad Incorrecta debe ser mayo o igual que 18";
        }
    }

    @Override
    public String saveColaboradorF(ColaboradorEntity Colaborador) {
        String sexo = Colaborador.getSexo().toLowerCase();
            if (sexo.equals("femenino")) {
                Colaborador.setSexo("Femenino");
                if (Colaborador.getEdad() >=18 && Colaborador.getEdad() <=60){
                    this.colaboradorRepositoryl.save(Colaborador);
                    return "ColaboradoraCorrecta.";
                }else{
                    return "erroredad";
                }
        } else {
            return "errorsexo";
        }
    }

    @Override
    public String saveColaboradorM(ColaboradorEntity Colaborador) {
        String sexo = Colaborador.getSexo().toLowerCase();
        if (sexo.equals("masculino")) {
            Colaborador.setSexo("Masculino");
            if (Colaborador.getEdad() >=18 && Colaborador.getEdad() <=65){
                this.colaboradorRepositoryl.save(Colaborador);
                return "ColaboradorCorrecto.";
            }else{
                return "erroredad";
            }
        } else {
            return "errorsexo";
        }
    }


    @Override
    public List<ColaboradorEntity> findColaborador() {
        return this.colaboradorRepositoryl.findAll();
    }

    @Override
    public String updateColaborador(ColaboradorEntity colaborador, String id) {
        if(this.colaboradorRepositoryl.findById(id) != null){
            colaborador.setId(id);
            this.colaboradorRepositoryl.save(colaborador);
            return "update";
        } else {
            return "updateError";
        }

    }


    @Override
    public ColaboradorEntity findColaboradorById(@PathVariable String idConsultor) {
        return this.colaboradorRepositoryl.findById(idConsultor).get();
    }
}





