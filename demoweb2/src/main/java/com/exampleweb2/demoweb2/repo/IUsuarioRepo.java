package com.exampleweb2.demoweb2.repo;


import com.exampleweb2.demoweb2.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IUsuarioRepo extends JpaRepository<Usuario,Integer> {
    Usuario findByNombre(String nombre);
}
