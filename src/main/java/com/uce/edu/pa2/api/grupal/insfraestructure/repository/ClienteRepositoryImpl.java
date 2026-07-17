package com.uce.edu.pa2.api.grupal.insfraestructure.repository;


import com.uce.edu.pa2.api.grupal.domain.model.Cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteRepositoryImpl implements PanacheRepositoryBase<Cliente,Integer>{

}
