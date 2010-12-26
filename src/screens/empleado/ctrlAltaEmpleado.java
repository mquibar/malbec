/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.empleado;

import entidades.Localidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modulo.empleado.ExpAltaEmpleado;
import screens.models.combos.LocalidadComboModel;

/**
 *
 * @author Manuel
 */
public class ctrlAltaEmpleado {
    private AltaEmpleado _pantalla;
    private ExpAltaEmpleado _gestorEmpleado;

    public ctrlAltaEmpleado(JDesktopPane panel) {
        _pantalla = new AltaEmpleado(this);
        panel.add(_pantalla);
        _gestorEmpleado = new ExpAltaEmpleado();
        _pantalla.getCbxLocalidad().setModel(new LocalidadComboModel(_gestorEmpleado.iniciarAltaEmpleado()));
        _pantalla.getTxtFecNac().setMaxSelectableDate(new Date());
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });
        _pantalla.getBtnSave().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
        _pantalla.setVisible(true);
        
    }

    void pressCancelButton(){
        if(JOptionPane.showConfirmDialog(_pantalla, "Desea cancela la operación?", "Cancelar", JOptionPane.YES_NO_OPTION)==0)
            _pantalla.dispose();
    }

    void pressOkButton(){
        String[] parametros= new String[9];
        Localidad localidad = ((LocalidadComboModel) _pantalla.getCbxLocalidad().getModel()).getSelected();

        parametros[0]=_pantalla.getTxtApellido().getText();
        parametros[1]=_pantalla.getTxtNombre().getText();
        parametros[2]=_pantalla.getTxtDni().getText();
        parametros[3]=tools.ManejaFechas.convertirDate(_pantalla.getTxtFecNac().getDate());
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
            if(JOptionPane.showConfirmDialog(_pantalla, "Operación realizada con exito. \n Desea realizar otra operación?","Guardado",JOptionPane.YES_NO_OPTION)!=0)
                _pantalla.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, "No se pudo completar la operación.\nError: "+ ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

}
