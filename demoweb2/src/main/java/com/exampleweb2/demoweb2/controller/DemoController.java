package com.exampleweb2.demoweb2.controller;




import com.exampleweb2.demoweb2.model.Persona;
import com.exampleweb2.demoweb2.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@Controller
public class DemoController {
    @Autowired(required = true)
    private IPersonaRepo repo;
    @Autowired
    private ReportService service;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,int idper,String ciudad ,int  sueldo,Model model) {
        Persona p=new Persona();
        p.setIdPersona(idper);
        p.setNombre(name);
        p.setCiudad(ciudad);
        p.setSueldo(sueldo);
        repo.save(p);

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


        return service.exportReport(format);
    }

}
