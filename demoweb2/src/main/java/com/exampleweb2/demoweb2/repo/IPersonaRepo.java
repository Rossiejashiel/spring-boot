package com.exampleweb2.demoweb2.repo;


import com.exampleweb2.demoweb2.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IPersonaRepo extends JpaRepository<Persona,Integer> {
}
