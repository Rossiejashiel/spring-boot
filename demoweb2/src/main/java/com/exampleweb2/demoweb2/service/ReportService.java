package com.exampleweb2.demoweb2.service;

import com.exampleweb2.demoweb2.model.Persona;
import com.exampleweb2.demoweb2.repo.IPersonaRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private IPersonaRepo repository;

    @Autowired
    private DataSource dataSource;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException, Exception {

        System.out.println("dataSource: "+dataSource);

        List<Persona> persona = repository.findAll();
        //File file = ResourceUtils.getFile("classpath:ciudad.jrxml");
        File file = ResourceUtils.getFile("classpath:ciudad.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSourceList = new JRBeanCollectionDataSource(persona);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createBy", "Java Techie");
        /**
         * Generea Reporte desde una lista de datos
         */
        //JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSourceList);
        /**
         * Generea Reporte desde una conexion de BBDD
         */
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource.getConnection());
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "C:\\Users\\USER\\Desktop" + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "C:\\Users\\USER\\Desktop\\" + "employees.pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\" + "employees.pdf");

        }
        return "reporte generado en el escritorio";
    }
}
