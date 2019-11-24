package com.rentcar.Colaborador.entity;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Data
@Document(collection = "colaboradores")
public class ColaboradorEntity implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @NotNull

    private String id;

    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private int edad;
    private String sexo;
    private String nivelpermiso;    //(Supervisor, Vendedor o Administrador)
    private LocalDate fechincorporacion; //(Fecha)
    private int sueldobase;    //(Supervisor = 1.500.000, Administrador = 1.000.000, Vendedor = 750.000)
    private double bonoservicio; // (%)
    private double sueldoMasBono; //sueldo+Bono




}
