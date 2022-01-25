package com.exampleweb2.demoweb2.controller;




import com.exampleweb2.demoweb2.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DemoController {
    @Autowired(required = true)
    private IPersonaRepo repo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Persona p=new Persona();
        p.setIdPersona(4);
        p.setNombre("Rossie2");
        repo.save(p);

        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping("/listar")
    public String greeting(Model model) {

        model.addAttribute("persona", repo.findAll());
        return "greeting";
    }
}
