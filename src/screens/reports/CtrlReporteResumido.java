/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import modulo.caja.ExpertoReportesCaja;
import screens.ctrlMainMenu;
import systemException.BussinesException;
import systemException.InvalidDataException;

/**
 *
 * @author MARIANO
 */
public class CtrlReporteResumido {
    private UIReportesFecha _pantalla;

    public CtrlReporteResumido() {
        _pantalla = new UIReportesFecha();
        loadScreen();
    }

    final void loadScreen(){
        _pantalla.getBtnExit().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressExitButton();
            }
        });
        _pantalla.getBtnPdf().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressPdfButton();
            }
        });
        _pantalla.getBtnXls().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressXlsButton();
            }
        });
        _pantalla.getChkDiario().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _pantalla.getTxtFechaFin().setEnabled(!_pantalla.getChkDiario().isSelected());
                if(_pantalla.getChkDiario().isSelected()) _pantalla.getTxtFechaFin().setDate(new Date());
            }
        });
        _pantalla.getTxtFechaInicio().setMaxSelectableDate(new Date());
        _pantalla.getTxtFechaInicio().setDate(new Date());
        _pantalla.getTxtFechaFin().setMaxSelectableDate(new Date());
        _pantalla.getTxtFechaFin().setDate(new Date());
        _pantalla.setTitle("Reporte resumido por fechas");
        _pantalla.setVisible(true);
        ctrlMainMenu.getDestktopPane().add(_pantalla);
    }

    void pressExitButton(){
        _pantalla.dispose();
    }

   void pressXlsButton() {
        try {
            if (_pantalla.getChkDiario().isSelected())
                _pantalla.getTxtFechaFin().setDate(_pantalla.getTxtFechaInicio().getDate());
            ExpertoReportesCaja.ReportResumidoXLS(_pantalla.getTxtFechaInicio().getDate(), _pantalla.getTxtFechaFin().getDate());
        } catch (InvalidDataException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Datos Invalidos", JOptionPane.ERROR_MESSAGE);
        } catch (BussinesException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error de sistema", JOptionPane.ERROR_MESSAGE);
        }

    }

    void pressPdfButton() {
        try {
            if (_pantalla.getChkDiario().isSelected())
                _pantalla.getTxtFechaFin().setDate(_pantalla.getTxtFechaInicio().getDate());
            ExpertoReportesCaja.ReportResumidoPDF(_pantalla.getTxtFechaInicio().getDate(), _pantalla.getTxtFechaFin().getDate());
        } catch (InvalidDataException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Datos Invalidos", JOptionPane.ERROR_MESSAGE);
        } catch (BussinesException ex) {
            JOptionPane.showMessageDialog(_pantalla, ex.getMessage(), "Error de sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

}
