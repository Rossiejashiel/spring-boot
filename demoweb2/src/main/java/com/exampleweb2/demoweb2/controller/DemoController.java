package com.exampleweb2.demoweb2.controller;


import com.exampleweb2.demoweb2.model.Persona;
import com.exampleweb2.demoweb2.service.PersonaService;
import com.exampleweb2.demoweb2.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Controller
public class DemoController {
    @Autowired(required = true)
    private IPersonaRepo repo;
    @Autowired
    private ReportService service;
    @Autowired
    private PersonaService personaService;
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, int idper, String ciudad, int sueldo, Model model) {
        Persona p = new Persona();
        p.setIdPersona(idper);
        p.setNombre(name);
        p.setCiudad(ciudad);
        p.setSueldo(sueldo);
        repo.<Persona>save(p);

        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/listar")
    public String greeting(Model model) {

        model.addAttribute("persona", repo.findAll());
        return "greeting";
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {

        try {
            service.exportReport(format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "greeting";
    }
    @GetMapping("/procedimiento/listar")
    public ResponseEntity listarprocedimiento(){
        List<Persona> lista =personaService.lista();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/procedimiento/listarid/{id}")
    public ResponseEntity listarporid(@PathVariable("id")int id ){
        List<Persona> lista =personaService.getById(id);
        return new ResponseEntity(lista, HttpStatus.OK);
    }
    @GetMapping("/procedimiento/{format}")
    public String crearReporte (@PathVariable String format, @RequestParam Integer id) throws FileNotFoundException, JRException {

        try {
           personaService.crearReporte(format,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "greeting";
    }
}
