/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author MARIANO
 */
public interface ExportStrategy {
    public void exportar(JasperPrint reporte);
}
