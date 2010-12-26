/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.empleado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import modulo.consultas.ExpertoConsultarEmpleado;
import screens.models.tablas.TableEmployees;

/**
 *
 * @author desarrollo
 */
public class ctrlListadoPersonal {
    ListadoPersonal _pantalla;

    public ctrlListadoPersonal(JDesktopPane panel) {
        _pantalla = new ListadoPersonal(this);
        panel.add(_pantalla);
        _pantalla.getTblEmployees().setModel(new TableEmployees((new ExpertoConsultarEmpleado()).listarEmpleados()));
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                _pantalla.dispose();
            }
        });
        _pantalla.setVisible(true);
    }



}
