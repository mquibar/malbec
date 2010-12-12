/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
            System.out.println(ex.getMessage() + "**********");
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

        _pantalla.getMnuSalir().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                exitSystem();
            }
        });

        _pantalla.getMnuListPersonal().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlListadoPersonal(_pantalla.getDesktopPane());
            }
        });

        _pantalla.getMnuRegTipoCaja().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new ctrlAltaTipoCaja(_pantalla.getDesktopPane());
            }
        });
        _pantalla.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
                exitSystem();
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    void exitSystem() {
        persistencia.Facade.getInstance().closeConection();
        System.exit(0);
    }
}
