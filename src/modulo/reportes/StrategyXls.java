/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

import com.lowagie.text.pdf.codec.Base64.InputStream;
import java.io.File;
import java.io.FileInputStream;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 *
 * @author MARIANO
 */
public class StrategyXls implements ExportStrategy {

    //http://www.jsanroman.net/2007/12/01/lanzando-informes-jasperreports-pdf-xls-desde-un-servlet/
    public void exportar(JasperPrint jasperPrint) {
//int bit;
//File f;
//InputStream in;
//ServletOutputStream out;
//String xlsFileName;
//        //Creacion del XLS
//JRXlsExporter exporter = new JRXlsExporter ();
//exporter.setParameter (JRExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter (JRExporterParameter.OUTPUT_FILE_NAME, xlsFileName);
//exporter.setParameter (JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
//exporter.exportReport();
//
//// En este punto ya esta Creado el XLS
//// Ahora leemos el fichero y forzamos al navegador a que nos descargue el fichero.
//f = new File (xlsFileName);
//
//response.setContentType ("application/vnd.ms-excel"); //Tipo de fichero.
//response.setHeader ("Content-Disposition", "attachment;filename=\"" + xlsFileName + "\""); //Configurar cabecera http
//
//in = new FileInputStream (f);
//out = response.getOutputStream ();
//
//bit = 256;
//while ((bit)&gt;= 0)
//{
//bit = in.read ();
//out.write (bit);
//}
//
//out.flush ();
//out.close ();
//in.close ();
//
    }

}
