/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.caja;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import modulo.reportes.ConectionReportAdmin;
import modulo.reportes.FactoryStrategy;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import systemException.BussinesException;
import systemException.InvalidDataException;

/**
 *
 * @author MARIANO
 */
public class ExpertoReportesCaja {

    private static final String REPORTE_DETALLADO_PDF="ReporteDiario.jrxml";
    private static final String REPORTE_RESUMIDO_PDF="repoteResumido.jrxml";
    private static final String REPORTE_DETALLADO_XLS="excelReport.jrxml";
    private static final String REPORTE_RESUMIDO_XLS="excelResumido.jrxml";

    public static void ReportDetalladoXLS(Date FInicio, Date FFin) throws InvalidDataException, BussinesException {
        ReporteDetallado(FInicio, FFin, FactoryStrategy.Exportacion.XLS, REPORTE_DETALLADO_XLS);
    }

    public static void ReportDetalladoPDF(Date FInicio, Date FFin) throws InvalidDataException, BussinesException {
        ReporteDetallado(FInicio, FFin, FactoryStrategy.Exportacion.PDFVIEW, REPORTE_DETALLADO_PDF);
    }
    
    public static void ReportResumidoXLS(Date FInicio, Date FFin)  throws InvalidDataException, BussinesException {
        ReporteDetallado(FInicio, FFin, FactoryStrategy.Exportacion.XLS, REPORTE_RESUMIDO_XLS);
    }
    
    public static void ReportResumidoPDF(Date FInicio, Date FFin) throws InvalidDataException, BussinesException{
        ReporteDetallado(FInicio, FFin, FactoryStrategy.Exportacion.PDFVIEW, REPORTE_RESUMIDO_PDF);
    }

    private static void ReporteDetallado(Date fInicio, Date Ffin, modulo.reportes.FactoryStrategy.Exportacion export, String fileJrxml) throws InvalidDataException, BussinesException {

       // se crea un HashMap como objeto que ALMACENARÁ LOS PARÁMETROS
       // que reciba la plantilla del reporte, en vista de que no enviamos
       // parametro alguno, solo se instancia el objeto.
       HashMap params = new HashMap();
       verificarFecha (fInicio, Ffin);
       params.put("FECHA_INICIO",fInicio);
       params.put("FECHA_FIN",Ffin);

       // Se obtiene la conexión que se empleará para poblar el reporte
       Connection con = ConectionReportAdmin.getConnection();

       // Se obtiene la ruta fisica de la plantilla del reporte
       InputStream io = ExpertoReportesCaja.class.getResourceAsStream("/reports/"+fileJrxml);

       try {

           // Compilamos la plantilla
           JasperReport objJasperReport = JasperCompileManager.compileReport(io);
           // Poblamos la plantilla con los datos de la BD y parametros
           JasperPrint objJasperPrint = JasperFillManager.fillReport(objJasperReport, params, con);
           // Exportamos el reporte
           (FactoryStrategy.getInstance().getStrategy(export)).exportar(objJasperPrint);
       }
       catch (Exception e) {
           e.printStackTrace();
           throw new BussinesException("Generar Reporte");

       }

    }

    private static void verificarFecha (Date inicio, Date fin) throws InvalidDataException {

        if (fin == null) {
            fin = inicio;
            return;
        }

        if (tools.ManejaFechas.diferencia(inicio, fin) < 0)
            throw new InvalidDataException("Fecha", fin);
     }
}
