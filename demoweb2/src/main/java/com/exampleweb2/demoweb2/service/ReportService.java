package com.exampleweb2.demoweb2.service;

import com.exampleweb2.demoweb2.model.Persona;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private IPersonaRepo repository;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        List<Persona> persona = repository.findAll();
        File file = ResourceUtils.getFile("classpath:ciudad.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(persona);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\USER\\Desktop" + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\USER\\Desktop\\" + "employees.pdf");

        }
        return "reporte generado en el escritorio";
    }
}
