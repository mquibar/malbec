/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.controllers;

import entidades.Parcela;
import entidades.TipoUva;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public ctrlLectorPeso() {
        _pantalla = new LectorPeso(this);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        _pantalla.getTxtFecha().setText(sdf.format(new Date()));
        selectedTipoUva();
    }

    public void selectedTipoUva() {
        popup = new PopUpParcela(_pantalla, false, this);
        popup.setModel(new TableTipoUva(null));
    }

    public void pressNextButton(int selectedRow) {
        switch (paso) {
            case 0:
                TipoUva uva = ((TableTipoUva) popup.getModel()).getSelectedItem(selectedRow);
                _pantalla.getTxtUva().setText("Tipo Uva");
                popup.setModel(new TableParcela(null));
                popup.getTxtCodigo().setEnabled(false);
                paso++;
                return;
            case 1:
                Parcela p = ((TableParcela) popup.getModel()).getSelectedItem(selectedRow);
                _pantalla.getTxtParcela().setText("Parcela");
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
            double peso = 8.70;
            String pesostr = String.valueOf(peso);
            for (int i = 0; pesostr.length() < 6; i++) {
                pesostr += "0";
            }
            parcearPeso(peso);
            _pantalla.getTxtPeso().setText(pesostr);
        } catch (Exception e) {
        }
    }

    private String parcearPeso(Double peso){
        int parteEntera = peso.intValue();
        float parteDecimal = peso.floatValue();
        System.out.println("&&&&&&&&&&&&6"+parteEntera);
        System.out.println("&&&&&&&&&&&&6"+parteDecimal);

        return "";
    }
}
