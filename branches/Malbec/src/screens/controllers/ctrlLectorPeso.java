/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.controllers;

import entidades.Parcela;
import entidades.TipoUva;
import java.text.SimpleDateFormat;
import java.util.Date;
import modulo.leerPeso.ExpertoLeerPeso;
import screens.LectorPeso;
import screens.PopUpParcela;
import screens.models.tablas.TableParcela;
import screens.models.tablas.TableTipoUva;

/**
 *
 * @author Manuel
 */
public class ctrlLectorPeso {

    private PopUpParcela popup;
    private LectorPeso _pantalla;
    private int paso = 0;
    private ExpertoLeerPeso _exp;

    public ctrlLectorPeso() {
        _pantalla = new LectorPeso(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        _pantalla.getTxtFecha().setText(sdf.format(new Date()));
        _exp= new ExpertoLeerPeso();
        selectedTipoUva();
    }

    public void selectedTipoUva() {
        popup = new PopUpParcela(_pantalla, false, this);
        popup.setModel(new TableTipoUva(_exp.listarTipoUva()));
    }

    public void pressNextButton(int selectedRow) {
        switch (paso) {
            case 0:
                TipoUva uva = ((TableTipoUva) popup.getModel()).getSelectedItem(selectedRow);
                _pantalla.getTxtUva().setText(uva.getNombre());
                popup.setModel(new TableParcela(_exp.listarParcelaDelTipo(uva)));
                popup.getTxtCodigo().setEnabled(false);
                paso++;
                return;
            case 1:
                Parcela p = ((TableParcela) popup.getModel()).getSelectedItem(selectedRow);
                _pantalla.getTxtParcela().setText(p.getNombre());
                _exp.asignarParcela(p);
                paso = 0;
                popup.dispose();
                return;
        }
    }

    public void pressOkButton() {
        /**
         * Comunicación con el experto para que le de el peso que tiene la balanza
         * Si existen exceptiones se mostraran por joption
         */
        try {
            double peso = _exp.leerPeso(_pantalla.getTxtBarCode().getText());
            _pantalla.getTxtPeso().setText(parcearPeso(peso));
        } catch (Exception e) {
        }
    }

    private String parcearPeso(Double peso){
        int parteEntera = peso.intValue();
        float parteDecimal = peso.floatValue();
        String entero = String.valueOf(parteEntera);
        String decimal = String.valueOf(parteDecimal).substring(entero.length()+1);
        for (int i = 0; entero.length() < 2; i++)
            entero = "0"+entero;
        for (int i = 0; decimal.length() < 3; i++)
            decimal +="0";
        
        return entero+","+decimal;
    }
}