package com.rentcar.Colaborador.repository;

import com.rentcar.Colaborador.entity.ColaboradorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

public interface ColaboradorRepository extends MongoRepository<ColaboradorEntity, Serializable> {
}
