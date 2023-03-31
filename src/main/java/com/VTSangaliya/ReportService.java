//package com.VTSangaliya;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.time.LocalDate;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.VTSangaliya.aarthikSahyog.AarthikSahyogAnnouncementEntity;
//import com.VTSangaliya.expenditure.ExpenditureEntity;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRParameter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
//import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
//import net.sf.jasperreports.export.SimpleExporterInput;
//import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
//import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
//
//@Service
//public class ReportService {
//	@Autowired
//	private HttpServletRequest request;
//	@Autowired
//	private HttpServletResponse response;
//
//	public void createExpenditureExcelReport(final List<ExpenditureEntity> expenditureList) throws JRException {
//        // Fetching the .jrxml file from the resources folder.
//       // final InputStream stream = this.getClass().getResourceAsStream("classpath:expenditureDetail.jrxml");
//        InputStream stream = request.getSession().getServletContext().getResourceAsStream("/jasper/expenditureDetail.jrxml");
//        // Compile the Jasper report from .jrxml to .japser
//        final JasperReport report = JasperCompileManager.compileReport(stream);
//
//        // Fetching the employees from the data source.
//        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(expenditureList);
//
//        // Adding the additional parameters to the pdf.
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "javacodegeek.com");
//        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
//
//        // Filling the report with the employee data and additional parameters information.
//        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
//        JRXlsxExporter exporter = new JRXlsxExporter();
//        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
//        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
//        exporter.setConfiguration(reportConfigXLS);
//      //  reportConfigXLS.setDetectCellType(true);
//       // reportConfigXLS.setIgnoreCellBorder(false);
//      //  reportConfigXLS.setShrinkToFit(true);
//       // reportConfigXLS.setCollapseRowSpan(false);
//        reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
//        reportConfigXLS.setRemoveEmptySpaceBetweenColumns(true);
//       // reportConfigXLS.setAutoFitPageHeight(true);
//
//
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        try {
//			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        response.setHeader("Content-Disposition", "attachment;filename="+LocalDate.now()+"-expenditureDetails.xlsx");
//        //response.setContentType("application/octet-stream");
//        response.setContentType("application/x-download");
//        exporter.exportReport();
//
//    }
//
//
//	public void createAnnouncementExcelReport(final List<AarthikSahyogAnnouncementEntity> announcementList) throws JRException {
//        // Fetching the .jrxml file from the resources folder.
//       // final InputStream stream = this.getClass().getResourceAsStream("classpath:expenditureDetail.jrxml");
//        InputStream stream = request.getSession().getServletContext().getResourceAsStream("/jasper/announcementDetail.jrxml");
//        // Compile the Jasper report from .jrxml to .japser
//        final JasperReport report = JasperCompileManager.compileReport(stream);
//
//        // Fetching the employees from the data source.
//        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(announcementList);
//
//        // Adding the additional parameters to the pdf.
//        final Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "javacodegeek.com");
//        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
//
//        // Filling the report with the employee data and additional parameters information.
//        final JasperPrint print = JasperFillManager.fillReport(report, parameters, source);
//        JRXlsxExporter exporter = new JRXlsxExporter();
//        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
//        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
//        exporter.setConfiguration(reportConfigXLS);
//      //  reportConfigXLS.setDetectCellType(true);
//       // reportConfigXLS.setIgnoreCellBorder(false);
//      //  reportConfigXLS.setShrinkToFit(true);
//       // reportConfigXLS.setCollapseRowSpan(false);
//        reportConfigXLS.setRemoveEmptySpaceBetweenRows(true);
//        reportConfigXLS.setRemoveEmptySpaceBetweenColumns(true);
//       // reportConfigXLS.setAutoFitPageHeight(true);
//
//
//        exporter.setExporterInput(new SimpleExporterInput(print));
//        try {
//			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        response.setHeader("Content-Disposition", "attachment;filename="+LocalDate.now()+"-AnnounceDetails.xlsx");
//        response.setContentType("application/octet-stream");
//        exporter.exportReport();
//
//    }
//
//}
