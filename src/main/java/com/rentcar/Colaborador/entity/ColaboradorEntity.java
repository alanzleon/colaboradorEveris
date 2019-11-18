package com.rentcar.Colaborador.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.util.calendar.LocalGregorianCalendar;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Document(value = "Colaborador")
public class ColaboradorEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull

    private String id;

    private String nombre;
    private String apellido;
    private String rut;
    private int edad;
    private String sexo;
    private String nivelpermiso;    //(Supervisor, Vendedor o Administrador)
    private LocalGregorianCalendar.Date fechincorporacion; //(Fecha)
    private int sueldobase;    //(Supervisor = 1.500.000, Administrador = 1.000.000, Vendedor = 750.000)
    private float bonoservicio; // (%)



}
