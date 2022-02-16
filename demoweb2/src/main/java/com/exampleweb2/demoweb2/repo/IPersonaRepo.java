package com.exampleweb2.demoweb2.repo;


import com.exampleweb2.demoweb2.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IPersonaRepo extends JpaRepository<Persona,Integer> {
    @Modifying
    @Query(value="select * from listar() ", nativeQuery = true)
    List<Persona> listaprocedure();
    @Query(value="select idPersona as id_persona, nombre,ciudad,sueldo from listarid(:idIn)", nativeQuery = true)
    List<Persona> listaid(@Param("idIn") Integer idiN);

}
