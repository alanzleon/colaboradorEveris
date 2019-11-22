package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import com.rentcar.Colaborador.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ColaboradorServiceImpls implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepositoryl;


    @Override
    public String saveColaborador(ColaboradorEntity colaborador) {
        try {
            if (colaborador.getRut() != null) {
                if (validarRut(colaborador.getRut())) {
                    if (colaborador.getEdad() >= 18 && colaborador.getEdad() < 100) {
                        if (colaborador.getNombre() != null) {
                            if (colaborador.getApellidoPaterno() != null) {
                                if (colaborador.getApellidoMaterno() != null) {
                                    if (colaborador.getSexo() != null) {
                                        String sexo = colaborador.getSexo().toLowerCase();
                                        if (sexo.equals("masculino") || sexo.equals("femenino")) {
                                            if (colaborador.getEdad() >= 18 && colaborador.getEdad() < 65){
                                                if (colaborador.getNivelpermiso() != null) {
                                                    String tipoPermiso = colaborador.getNivelpermiso().toLowerCase();

                                                    if (tipoPermiso.equals("administrador")) {
                                                        colaborador.setSueldobase(1000000);
                                                    } else if (tipoPermiso.equals("vendedor")) {
                                                        colaborador.setSueldobase(750000);
                                                    } else {
                                                        tipoPermiso.equals("supervisor");
                                                        colaborador.setSueldobase(1500000);
                                                    }
                                                    this.colaboradorRepositoryl.save(colaborador);

                                                } else {
                                                    return "EmptyNivelPermiso";
                                                }
                                            }else{
                                                return "InvalidSexEd";
                                            }

                                        } else {
                                            return "InvalidSexo";
                                        }
                                    } else {
                                        return "EmptySexo";
                                    }
                                } else {
                                    return "EmptyApellidoMaterno";
                                }
                            } else {
                                return "EmptyApellidoPaterno";
                            }
                        } else {
                            return "EmptyNombre";
                        }
                    } else {
                        return "NoMayoredad";
                    }
                } else {
                    return "invalidRut";
                }
            } else {
                return "EmptyRut";
            }
        } catch (Exception ex) {
            return "" + ex;
        }
        return "ok";
    }
/*
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

    */



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
    public ColaboradorEntity findColaboradorById (@PathVariable String idColaborador) {
        return  this.colaboradorRepositoryl.findById(idColaborador).get();
    }

    public boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut =  rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }

    private  boolean validarTelefono(String telefono) {
        Pattern pattern = Pattern.compile("^(\\+?56)?(\\s?)(0?9)(\\s?)[9876543]\\d{7}$");
        return pattern.matcher(telefono).matches();
    }

}







