package com.exampleweb2.demoweb2.rest;

import com.exampleweb2.demoweb2.model.Persona;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class RestDemoController {

    @Autowired
    private IPersonaRepo repo;

    @GetMapping
    public List<Persona> listar() {
        return repo.findAll();
    }
    @PostMapping
    public void insertar(@RequestBody Persona per) {
        repo.save(per);
    }

    @PutMapping
    public void modificar(@RequestBody Persona per) {
        repo.save(per);
    }

    @DeleteMapping(value="/{id}")
    public void eliminar(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }
}
