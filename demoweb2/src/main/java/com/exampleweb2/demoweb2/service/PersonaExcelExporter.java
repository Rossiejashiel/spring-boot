package com.exampleweb2.demoweb2.service;

import com.exampleweb2.demoweb2.model.Persona;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.ss.usermodel.FillPatternType;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonaExcelExporter {

    private Workbook workbook;

    private Sheet sheet;

    private List<Persona> listPersona;
    public PersonaExcelExporter(List<Persona> listPersona) {
        this.listPersona = listPersona;
        workbook=new HSSFWorkbook();
        sheet=workbook.createSheet("Persona");// nombre del libro

    }

    private  void writeHeaderRow(){
        Row row =sheet.createRow(0);
        Cell cell=row.createCell(0);
        cell.setCellValue("Persona ID");

        cell=row.createCell(1);
        cell.setCellValue("Nombre");


        cell=row.createCell(2);
        cell.setCellValue("Ciudad");

        cell=row.createCell(3);
        cell.setCellValue("Sueldo");


    }
    private void writeDataRows(){
        int rowCount =1;
        for (Persona persona:listPersona){
            Row row=sheet.createRow(rowCount);


            row.createCell(0).setCellValue(persona.getIdPersona());
            row.createCell(1).setCellValue(persona.getNombre());
            row.createCell(2).setCellValue(persona.getCiudad());
            row.createCell(3).setCellValue(persona.getSueldo());


            rowCount++;
        }

    }
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        ServletOutputStream outputStream= response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
