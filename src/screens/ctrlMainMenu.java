/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import screens.produccion.ctrlAltaTipoUva;
import screens.produccion.ctrlAltaParcela;
import screens.produccion.ctrlLectorPeso;
import screens.produccion.ctrlAltaTipoCaja;
import screens.empleado.ctrlAltaEmpleado;
import screens.empleado.ctrlListadoPersonal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.JDesktopPane;
import javax.swing.UnsupportedLookAndFeelException;
import screens.reports.CtrlReporteDetallado;
import screens.reports.CtrlReporteResumido;

/**
 *
 * @author desarrollo
 */
public class ctrlMainMenu {

    private static MainMenu _pantalla;

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

        _pantalla.getMnuRepDetallado().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new CtrlReporteDetallado();
            }
        });

        _pantalla.getMnuRepResumido().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new CtrlReporteResumido();
            }
        });

        _pantalla.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                exitSystem();
            }
        });
    }

    void exitSystem() {
        persistencia.Facade.getInstance().closeConection();
        System.exit(0);
    }

    public static JDesktopPane getDestktopPane(){
        return _pantalla.getDesktopPane();
    }
}
