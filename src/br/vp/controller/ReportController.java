package br.vp.controller;

import java.util.List;

import br.vp.model.Vendas;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
 
public class ReportController 
{

	private String pathToReportPackage;
	
	public ReportController() {
		this.pathToReportPackage = "c:\\git\\vendas-plus\\report\\";
	}
	
	
	public void imprimir(List<Vendas> vendas) throws Exception	
	{
		JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "RelatorioVendas.jrxml");
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(vendas));
 
		JasperExportManager.exportReportToPdfFile(print, this.pathToReportPackage + "Relatorio_Vendas.pdf");		
	}
 
	public String getPathToReportPackage() {
		return this.pathToReportPackage;
	}
}