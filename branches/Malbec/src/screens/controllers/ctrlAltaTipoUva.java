/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.controllers;

import entidades.Parcela;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import modulo.parcelaTipoUva.ExpAltaTipoUva;
import screens.AltaTipoUva;
import screens.models.tablas.TableParcela;

/**
 *
 * @author desarrollo
 */
public class ctrlAltaTipoUva {

    private AltaTipoUva _pantalla;
    private ExpAltaTipoUva _gestorAlta;
    private TableParcela _todas, _asignadas;

    public ctrlAltaTipoUva(JDesktopPane panel) {
        _pantalla = new AltaTipoUva(this);
        panel.add(_pantalla);
        _gestorAlta = new ExpAltaTipoUva();
        _todas = new TableParcela(_gestorAlta.iniciarAltaTipoUva());
        _asignadas = new TableParcela(new ArrayList<Parcela>());
        _pantalla.getTblParcela().setModel(_todas);
        _pantalla.getTblAsignadas().setModel(_asignadas);

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

        _pantalla.getBtnAddOne().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddOneButton();
            }
        });
        _pantalla.getBtnAddAll().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressAddAllButton();
            }
        });

        _pantalla.getBtnRemoveOne().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressRemoveOneButton();
            }
        });
        _pantalla.getBtnRemoveAll().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressRemoveAllButton();
            }
        });
        
        _pantalla.setVisible(true);
    }

    void pressOkButton(){
        try {
            _gestorAlta.guardarTipoUva(_asignadas.getListRow(), _pantalla.getTxtNombre().getText(), _pantalla.getTxtCodigo().getText(), _pantalla.getTxtDescripcion().getText());
            JOptionPane.showMessageDialog(_pantalla, "Operación realizada con exito");
            _pantalla.getTxtCodigo().setText("");
            _pantalla.getTxtDescripcion().setText("");
            _pantalla.getTxtNombre().setText("");
            _todas = new TableParcela(_gestorAlta.iniciarAltaTipoUva());
        _asignadas = new TableParcela(new ArrayList<Parcela>());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, "No se pudo completar la operación \n Error: "+ex.getMessage(), "Error al guarda", JOptionPane.ERROR_MESSAGE);
        }
    }

    void pressCancelButton(){
        if(JOptionPane.showConfirmDialog(_pantalla, "Desea cancelar la operacion?", "Confirmar", JOptionPane.YES_NO_OPTION)!=0)
            return;
        _pantalla.dispose();
    }

    void pressAddOneButton(){
        int rowSelected = _pantalla.getTblParcela().getSelectedRow();
        _asignadas.addRow(_todas.getSelectedItem(rowSelected));
        _todas.removeRow(_todas.getSelectedItem(rowSelected));
        if(_todas.getListRow().isEmpty()){
            _pantalla.getBtnAddOne().setEnabled(false);
            _pantalla.getBtnAddAll().setEnabled(false);
        }
    }

    void pressAddAllButton(){
        _asignadas.addRowAll(_todas.getListRow());
        _todas.clear();
        _pantalla.getBtnAddOne().setEnabled(false);
        _pantalla.getBtnAddAll().setEnabled(false);

    }

    void pressRemoveOneButton(){

        int rowSelected = _pantalla.getTblParcela().getSelectedRow();
        _todas.addRow(_asignadas.getSelectedItem(rowSelected));
        _asignadas.removeRow(_asignadas.getSelectedItem(rowSelected));
        if(_asignadas.getListRow().isEmpty()){
            _pantalla.getBtnRemoveOne().setEnabled(false);
            _pantalla.getBtnRemoveAll().setEnabled(false);
        }
    }

    void pressRemoveAllButton(){
        _todas.addRowAll(_asignadas.getListRow());
        _asignadas.clear();
        _pantalla.getBtnRemoveOne().setEnabled(false);
        _pantalla.getBtnRemoveAll().setEnabled(false);

    }
}
