package com.rentcar.Colaborador.Service;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import com.rentcar.Colaborador.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ColaboradorServiceImpls implements ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepositoryl;

    String[] nivelPermiso = {"administrador", "supervisor", "vendedor"};

    @Override
    public String saveColaborador(ColaboradorEntity colaborador) {


        try {
            if (colaborador.getNombre() != null) {
                if (colaborador.getApellidoPaterno() != null) {
                    if (colaborador.getApellidoMaterno() != null) {
                        if (colaborador.getRut() != null) {
                            if (validarRut(colaborador.getRut())) {
                                //Se le da formato al rut xx.xxx.xxx-x
                                colaborador.setRut(formatearRut(colaborador.getRut()));
                                if (colaboradorExist(colaborador.getRut())) {
                                    if (colaborador.getEdad() != 0) {
                                        if (colaborador.getEdad() >= 18 && colaborador.getEdad() < 100) {
                                            if (colaborador.getSexo() != null) {
                                                String sexo = colaborador.getSexo().toLowerCase();
                                                if (sexo.equals("masculino")) {
                                                    if (colaborador.getEdad() >= 18 && colaborador.getEdad() <= 65) {
                                                        if (colaborador.getNivelpermiso() != null) {

                                                            String NivelPermiso = colaborador.getNivelpermiso().toLowerCase();
                                                            colaborador.setNivelpermiso(NivelPermiso);

                                                            if (Arrays.asList(nivelPermiso).contains(NivelPermiso)) {
                                                                if (NivelPermiso.equals("administrador")) {
                                                                    colaborador.setSueldobase(1000000);

                                                                    LocalDate fechaInicio = colaborador.getFechincorporacion();
                                                                    LocalDate fechaActual = LocalDate.now();

                                                                    Period años = Period.between(fechaInicio, fechaActual);
                                                                    int añosEntreFechas = años.getYears();
                                                                    int sueldoBase = colaborador.getSueldobase();
                                                                    int anioServicio = sueldoBase * 12;
                                                                    double bono = (anioServicio * 0.2);
                                                                    double sueldoMasBono = (sueldoBase + bono);
                                                                    colaborador.setBonoservicio(bono);
                                                                    colaborador.setSueldoMasBono(sueldoMasBono);


                                                                } else if (NivelPermiso.equals("vendedor")) {
                                                                    colaborador.setSueldobase(750000);

                                                                    LocalDate fechaInicio = colaborador.getFechincorporacion();
                                                                    LocalDate fechaActual = LocalDate.now();

                                                                    Period años = Period.between(fechaInicio, fechaActual);
                                                                    int añosEntreFechas = años.getYears();
                                                                    int sueldoBase = colaborador.getSueldobase();
                                                                    int anioServicio = sueldoBase * 12;
                                                                    double bono = (anioServicio * 0.2);
                                                                    double sueldoMasBono = (sueldoBase + bono);
                                                                    colaborador.setBonoservicio(bono);
                                                                    colaborador.setSueldoMasBono(sueldoMasBono);

                                                                } else {
                                                                    NivelPermiso.equals("supervisor");
                                                                    colaborador.setSueldobase(1500000);

                                                                    LocalDate fechaInicio = colaborador.getFechincorporacion();
                                                                    LocalDate fechaActual = LocalDate.now();

                                                                    Period años = Period.between(fechaInicio, fechaActual);
                                                                    int añosEntreFechas = años.getYears();
                                                                    int sueldoBase = colaborador.getSueldobase();
                                                                    int anioServicio = sueldoBase * 12;
                                                                    double bono = (anioServicio * 0.2);
                                                                    double sueldoMasBono = (sueldoBase + bono);
                                                                    colaborador.setBonoservicio(bono);
                                                                    colaborador.setSueldoMasBono(sueldoMasBono);

                                                                }

                                                                this.colaboradorRepositoryl.save(colaborador);


                                                            } else {
                                                                return "InvalidobtenerPermisoM";
                                                            }
                                                        } else {
                                                            return "EmptyNivelPermisoM";
                                                        }
                                                    } else {
                                                        return "InvalidEdadM";
                                                    }
                                                } else if (sexo.equals("femenino")) {
                                                    if (colaborador.getEdad() >= 18 && colaborador.getEdad() <= 60) {
                                                        if (colaborador.getNivelpermiso() != null) {

                                                            String NivelPermiso = colaborador.getNivelpermiso().toLowerCase();
                                                            colaborador.setNivelpermiso(NivelPermiso);

                                                            if (Arrays.asList(nivelPermiso).contains(NivelPermiso)) {


                                                                if (NivelPermiso.equals("administrador")) {
                                                                    colaborador.setSueldobase(1000000);
                                                                } else if (NivelPermiso.equals("vendedor")) {
                                                                    colaborador.setSueldobase(750000);
                                                                } else {
                                                                    NivelPermiso.equals("supervisor");
                                                                    colaborador.setSueldobase(1500000);
                                                                }

                                                                this.colaboradorRepositoryl.save(colaborador);

                                                            } else {
                                                                return "InvalidobtenerPermisoF";
                                                            }
                                                        } else {
                                                            return "EmptyNivelPermisoF";
                                                        }
                                                    }
                                                } else {
                                                    return "InvalidSexo";
                                                }
                                            } else {
                                                return "EmptySexo";
                                            }
                                        } else {
                                            return "NoMayoredad";
                                        }
                                    } else {
                                        return "Emptyedad";
                                    }
                                } else {
                                    return "rutExistente";
                                }
                            } else {
                                return "invalidRut";
                            }

                        } else {
                            return "EmptyRut";
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
        } catch (Exception ex) {
            return "" + ex;
        }
        return "ok";
    }

    @Override
    public ColaboradorEntity findColaboradorByRut(String rut) {
        String rutFormateado = formatearRut(rut);
        return this.colaboradorRepositoryl.findColaboradorByRut(rutFormateado);
    }


    @Override
    public List<ColaboradorEntity> findColaborador() {
        return this.colaboradorRepositoryl.findAll();
    }


    @Override
    public String updateColaborador(ColaboradorEntity colaborador, String id) {
        //Valido que exista el Colaborador
        if (this.colaboradorRepositoryl.findById(id) != null) {
            ColaboradorEntity colaboradorbd = this.colaboradorRepositoryl.findColaboradorById(id);
            if (colaborador.getNombre() != null) {
                colaboradorbd.setNombre(colaborador.getNombre());
            }
            if (colaborador.getApellidoPaterno() != null) {
                colaboradorbd.setApellidoPaterno(colaborador.getApellidoPaterno());
            }
            if (colaborador.getApellidoMaterno() != null) {
                colaboradorbd.setApellidoMaterno(colaborador.getApellidoMaterno());
            }
            if (colaborador.getEdad() >= 18 && colaborador.getEdad() <= 100) {
                colaboradorbd.setEdad(colaborador.getEdad());
            } else {
                return "invalidEdad";
            }
            if (colaborador.getSexo() != null) {
                String sexo = colaborador.getSexo().toLowerCase();
                if (sexo.equals("masculino") || sexo.equals("femenino")) {
                    if (colaborador.getEdad() >= 18 || colaborador.getEdad() <= 65) {
                        if (colaborador.getNivelpermiso() != null) {
                            String tipoPermiso = colaborador.getNivelpermiso().toLowerCase();
                            if (tipoPermiso.equals("administrador")) {
                                colaboradorbd.setSueldobase(1000000);
                            } else if (tipoPermiso.equals("vendedor")) {
                                colaboradorbd.setSueldobase(750000);
                            } else {
                                tipoPermiso.equals("supervisor");
                                colaboradorbd.setSueldobase(1500000);
                            }

                        } else {
                            return "EmptyNivelPermiso";
                        }
                    } else {
                        return "InvalidSexEd";
                    }

                } else {
                    return "InvalidSexo";
                }
            }

            this.colaboradorRepositoryl.save(colaboradorbd);
            return "update";
        } else {
            return "notFound";
        }
    }


    public String formatearRut(String rut) {
        int cont = 0;
        String format;
        if (rut.length() == 0) {
            return "";
        } else {
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            format = "-" + rut.substring(rut.length() - 1);
            for (int i = rut.length() - 2; i >= 0; i--) {
                format = rut.substring(i, i + 1) + format;
                cont++;
                if (cont == 3 && i != 0) {
                    format = "." + format;
                    cont = 0;
                }
            }
            return format;
        }
    }


    public boolean colaboradorExist(String rut) {
        if (this.colaboradorRepositoryl.findColaboradorByRut(rut) == null) {
            return false;
        } else {
            return true;
        }
    }


    public boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
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

}
