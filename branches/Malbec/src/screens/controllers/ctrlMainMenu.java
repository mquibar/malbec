/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.UnsupportedLookAndFeelException;
import screens.MainMenu;

/**
 *
 * @author desarrollo
 */
public class ctrlMainMenu {

    private MainMenu _pantalla;

    public static void main(String[] args) {

        try {
                    try {
                        //                    javax.swing.UIManager.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel());
                        javax.swing.UIManager.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaSkyMetallicLookAndFeel());
                    } catch (UnsupportedLookAndFeelException ex) {
                        System.out.println(ex.getMessage());
                    }

                } catch (ParseException ex) {
                    System.out.println(ex.getMessage()+"**********");
                    ex.getStackTrace();
                }
        new ctrlMainMenu();


    }
    public ctrlMainMenu() {
        _pantalla = new MainMenu(this);
        _pantalla.setVisible(true);
        _pantalla.getMnuIniciarDia().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlLectorPeso(_pantalla.getDesktopPane());
            }
        });
        _pantalla.getMnuRegParcela().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlAltaParcela(_pantalla.getDesktopPane());
            }
        });
        _pantalla.getMnuRegTipoUva().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlAltaTipoUva(_pantalla.getDesktopPane());
            }
        });

        _pantalla.getMnuRegPersonal().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlAltaEmpleado(_pantalla.getDesktopPane());
            }
        });
    }


}
