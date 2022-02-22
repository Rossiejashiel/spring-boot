package com.exampleweb2.demoweb2.mapper;

import com.exampleweb2.demoweb2.model.Persona;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Mapper
public interface PersonaMapper {
    @Select("select id_persona idPersona ,nombre,ciudad,sueldo from persona")
    List<Persona> findAll();
}
