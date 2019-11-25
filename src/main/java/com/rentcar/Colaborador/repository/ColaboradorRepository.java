package com.rentcar.Colaborador.repository;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import jdk.vm.ci.meta.Local;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;

@Repository
public interface ColaboradorRepository extends MongoRepository<ColaboradorEntity, Serializable> {


    ColaboradorEntity findOneByRut(String rut);

}
