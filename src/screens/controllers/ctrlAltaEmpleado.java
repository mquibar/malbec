/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.controllers;

import entidades.Localidad;
import javax.swing.JOptionPane;
import modulo.empleado.ExpAltaEmpleado;
import screens.AltaEmpleado;
import screens.models.combos.LocalidadComboModel;

/**
 *
 * @author Manuel
 */
public class ctrlAltaEmpleado {
    private AltaEmpleado _pantalla;
    private ExpAltaEmpleado _gestorEmpleado;

    public ctrlAltaEmpleado() {
        _pantalla = new AltaEmpleado(this);
        _gestorEmpleado = new ExpAltaEmpleado();
        _pantalla.getCbxLocalidad().setModel(new LocalidadComboModel(_gestorEmpleado.iniciarAltaEmpleado()));
        
    }

    void pressCancelButton(){
        _pantalla.dispose();
    }

    void pressOkButton(){
        String[] parametros= new String[9];
        Localidad localidad = ((LocalidadComboModel) _pantalla.getCbxLocalidad().getModel()).getSelected();

        parametros[0]=_pantalla.getTxtApellido().getText();
        parametros[1]=_pantalla.getTxtNombre().getText();
        parametros[2]=_pantalla.getTxtDni().getText();
        parametros[3]=_pantalla.getTxtFecNac().getDateFormatString();
        parametros[4]=_pantalla.getTxtCodigo().getText();
        parametros[5]=_pantalla.getTxtCalle().getText();
        parametros[6]=_pantalla.getTxtNumero().getText();
        parametros[7]=_pantalla.getTxtPiso().getText();
        parametros[8]=_pantalla.getTxtDepto().getText();

        for (String string : parametros) {
            if(string.isEmpty()){
                JOptionPane.showMessageDialog(_pantalla, "Debe completar todos los campos", "Error da carga", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        try {
            _gestorEmpleado.guardar(localidad, parametros);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
