/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.produccion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modulo.parcelaTipoUva.ExpAltaParcela;

/**
 *
 * @author Manuel
 */
public class ctrlAltaParcela {

    private AltaParcela _pantalla;

    public ctrlAltaParcela(JDesktopPane panel) {
        _pantalla = new AltaParcela(this);
        panel.add(_pantalla);
        _pantalla.getBtnOk().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });
        _pantalla.setVisible(true);

    }

    void pressCancelButton(){
        _pantalla.dispose();
    }

    void pressOkButton(){
        try {
            (new ExpAltaParcela()).guardarParcela(_pantalla.getTxtNombre().getText(), _pantalla.getTxtCodigo().getText(), _pantalla.getTxtDescripcion().getText());
            if(JOptionPane.showConfirmDialog(_pantalla, "Operación realizada con exito. \n Desea realizar otra operación?","Guardado",JOptionPane.YES_NO_OPTION)!=0)
                pressCancelButton();
            _pantalla.getTxtCodigo().setText("");
            _pantalla.getTxtDescripcion().setText("");
            _pantalla.getTxtCodigo().setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, "No se pudo completar la operación.\nError: "+ ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
        
    }


    
}
