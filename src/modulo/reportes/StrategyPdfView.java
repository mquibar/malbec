/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author MARIANO
 */
public class StrategyPdfView implements ExportStrategy {

    public void exportar(JasperPrint reporte) {
        JasperViewer.viewReport(reporte);
    }

}
