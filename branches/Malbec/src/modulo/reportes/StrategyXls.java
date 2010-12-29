/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

import java.io.File;
import java.util.Date;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;


public class StrategyXls implements ExportStrategy {

    
    public void exportar(JasperPrint reporte) {
        String xlsFileName =getPath()+ java.io.File.separator+"reporte_"+tools.ManejaFechas.convertirDate(new Date(),"dd-MM-yy_HH.mm.ss")+".xls";

        try {
        //Creacion del XLS
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter (JRExporterParameter.JASPER_PRINT, reporte);
        exporter.setParameter (JRExporterParameter.OUTPUT_FILE_NAME, xlsFileName);
        exporter.setParameter (JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exporter.exportReport();
        }

        catch (Exception e) {
           System.out.println("se la hecho!" + e.getMessage());
       
       }

    }

    private String getPath(){
        String path = tools.WorkingDirectory.getWorkingDirectoryPath()+ java.io.File.separator+"Reportes";
        java.io.File file = new File(path);
        if(!file.exists())
            file.mkdir();
        return path;
    }

}
