package br.vp.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import br.vp.controller.VendedorController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportServlet extends HttpServlet {

	VendedorController controller = new VendedorController();
	String url = "c:\\git\\vendas-plus\\report\\";
	
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	int id = Integer.parseInt(request.getParameter("id"));
    	byte[] pdfReport = null;
    	
    	try {
    		JasperReport reportStream = JasperCompileManager.compileReport(url + "RelatorioVendas.jrxml");
    		JasperPrint report = JasperFillManager.fillReport(reportStream, null, new JRBeanCollectionDataSource(controller.getNotas(id)));
    		pdfReport = JasperExportManager.exportReportToPdf(report);
    		//pdfReport = JasperRunManager.runReportToPdf(reportStream, null, new JRBeanCollectionDataSource(controller.getNotas(id)));
    		
    	} catch (Exception e) {
    		System.out.println("Erro na geração de pdf");
    	}

    	
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Type", "application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=historico-vendas.pdf");
        response.setContentLength((int) pdfReport.length);
        
        outputStream.write(pdfReport);
    }
}