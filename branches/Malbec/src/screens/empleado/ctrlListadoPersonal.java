/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.ListSelectionModel;
import modulo.consultas.ExpertoConsultarEmpleado;
import screens.models.tablas.TableEmployees;

/**
 *
 * @author desarrollo
 */
public class ctrlListadoPersonal {
    private ListadoPersonal _pantalla;
    private TableEmployees _tEmpleados;

    public ctrlListadoPersonal(JDesktopPane panel) {
        _pantalla = new ListadoPersonal();
        panel.add(_pantalla);
        _tEmpleados = new TableEmployees((new ExpertoConsultarEmpleado()).listarEmpleados());
        _pantalla.getTblEmployees().setModel(_tEmpleados);
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _pantalla.dispose();
            }
        });
        _pantalla.getBtnEdit().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressEditButton();
            }
        });
        _pantalla.getTblEmployees().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _pantalla.getTblEmployees().setRowSelectionInterval(0, 0);
        _pantalla.setVisible(true);
    }

    void pressEditButton(){
        _pantalla.setVisible(false);
        new CtrlModificarEmpleado(_tEmpleados.getSelectedItem(_pantalla.getTblEmployees().getSelectedRow()),this);
    }

    public void habilitar(){
        _tEmpleados.fireTableDataChanged();
        _pantalla.setVisible(true);
    }

}
