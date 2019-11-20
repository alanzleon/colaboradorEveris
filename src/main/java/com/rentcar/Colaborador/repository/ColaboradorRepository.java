package com.rentcar.Colaborador.repository;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ColaboradorRepository extends MongoRepository<ColaboradorEntity, Serializable> {

    public ColaboradorEntity findColaboradorById(String id);



}
