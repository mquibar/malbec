/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import screens.AltaParcela;

/**
 *
 * @author Manuel
 */
public class ctrlAltaParcela {

    private AltaParcela _pantalla;

    public ctrlAltaParcela() {
        _pantalla = new AltaParcela(this);
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

    }

    void pressCancelButton(){
        _pantalla.dispose();
    }

    void pressOkButton(){
        
    }


    
}
