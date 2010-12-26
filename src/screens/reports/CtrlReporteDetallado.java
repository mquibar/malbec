/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import screens.ctrlMainMenu;

/**
 *
 * @author MARIANO
 */
public class CtrlReporteDetallado {
    private UIReportesFecha _pantalla;

    public CtrlReporteDetallado() {
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
        _pantalla.setTitle("Reporte detallado por fechas");
        _pantalla.setVisible(true);
        ctrlMainMenu.getDestktopPane().add(_pantalla);
    }

    void pressExitButton(){
        _pantalla.dispose();
    }

    void pressXlsButton(){

    }

    void pressPdfButton(){

    }

}
