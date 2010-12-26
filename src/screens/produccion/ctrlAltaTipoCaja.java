/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.produccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modulo.tipoCaja.ExpertoAltaTipoCaja;

/**
 *
 * @author Juan
 */
public class ctrlAltaTipoCaja {
    private AltaTipoCaja _pantalla;

    public ctrlAltaTipoCaja(JDesktopPane panel){

        _pantalla = new AltaTipoCaja();
        panel.add(_pantalla);
        cargarPantalla();

        _pantalla.setVisible(true);
    }

    final void cargarPantalla(){
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });

        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
    }
    void pressCancelButton(){
        if(JOptionPane.showConfirmDialog(_pantalla, "Desea cancela la operación?", "Cancelar", JOptionPane.YES_NO_OPTION)==0)
            _pantalla.dispose();
    }
    void pressOkButton(){
        ExpertoAltaTipoCaja gestor = new ExpertoAltaTipoCaja();
        try {
            gestor.guardar(Double.valueOf(_pantalla.getTxtPeso().getText()), null, _pantalla.getTxtNombre().getText(), _pantalla.getTxtDescripcion().getText());
            if(JOptionPane.showConfirmDialog(_pantalla, "Operación realizada con exito. \n Desea realizar otra operación?","Guardado",JOptionPane.YES_NO_OPTION)!=0)
                _pantalla.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, "ERROR: "+ ex, "Error al intentar guardar",JOptionPane.ERROR_MESSAGE);
        }
    }

}
