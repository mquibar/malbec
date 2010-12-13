/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.controllers;

import entidades.Parcela;
import entidades.TipoCaja;
import entidades.TipoUva;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDesktopPane;
import modulo.caja.ExpertoCargarCaja;
import screens.LectorPeso;
import screens.PopUpParcela;
import screens.models.tablas.TableParcela;
import screens.models.tablas.TableTipoCaja;
import screens.models.tablas.TableTipoUva;
import systemException.InvalidDataException;

/**
 *
 * @author Manuel
 */
public final class ctrlLectorPeso {

    private PopUpParcela popup;
    private LectorPeso _pantalla;
    private int paso = 0;
    private ExpertoCargarCaja _exp;
    private long tiempo = 0;
    private static java.awt.Color VERDE = new java.awt.Color(0, 153, 0);
    private static java.awt.Color ROJO = new java.awt.Color(250, 9, 45);

    public ctrlLectorPeso(JDesktopPane desktop) {
        _pantalla = new LectorPeso(this);
        popup = new PopUpParcela(this);
        desktop.add(_pantalla);
        desktop.add(popup);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        _pantalla.getTxtFecha().setText(sdf.format(new Date()));
        _exp = new ExpertoCargarCaja();
        _pantalla.getTxtBarCode().addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }

            public void keyReleased(KeyEvent e) {
                checkKey(e);
            }

            private void checkKey(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (isTimeOut()) {
                        pressOkButton();
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                        _pantalla.getTxtBarCode().setText("");
                    }
                }
            }
        });
        selectedTipoUva();

    }

    public void selectedTipoUva() {
        popup.setModel(new TableTipoUva(_exp.listarTipoUva()));
        popup.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                popup.dispose();
            }
        });
        popup.getTxtNombre().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int idx = popup.getTblTipoUva().getSelectedRow();
                    if (idx < 0) {
                        idx = 0;
                    }
                    pressNextButton(idx);
                }
            }
        });
        popup.getTxtCodigo().addKeyListener(new KeyAdapter() {
        @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int idx = popup.getTblTipoUva().getSelectedRow();
                    if (idx < 0) {
                        idx = 0;
                    }
                    pressNextButton(idx);
                }
            }});
        popup.setVisible(true);
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
                popup.setModel(new TableTipoCaja(_exp.listarTipoCaja()));
                paso++;
                return;
            case 2:
                TipoCaja tc = ((TableTipoCaja) popup.getModel()).getSelectedItem(selectedRow);
                _pantalla.getTxtTipo().setText(tc.getNombreTipo());
                _exp.asignarTipoCaja(tc);
                paso = 0;
                popup.dispose();
                _pantalla.setVisible(true);
        }
    }

    public void pressOkButton() {
        /**
         * Comunicación con el experto para que le de el peso que tiene la balanza
         * Si existen exceptiones se mostraran por joption
         */
        double peso = 0;
        java.awt.Color fg = null;
        try {
            peso = _exp.cargarCaja(_pantalla.getTxtBarCode().getText());
            fg = VERDE;
        } catch (InvalidDataException ie) {
            peso = Double.valueOf(ie.getValor().toString());
            Toolkit.getDefaultToolkit().beep();
            fg = ROJO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        _pantalla.getTxtPeso().setForeground(fg);
        _pantalla.getTxtPeso().setText(parcearPeso(peso));
        _pantalla.getTxtBarCode().setText("");
        setTiempo();
    }

    private String parcearPeso(Double peso) {
        int parteEntera = peso.intValue();
        float parteDecimal = peso.floatValue();
        String entero = String.valueOf(parteEntera);
        String decimal = String.valueOf(parteDecimal).substring(entero.length() + 1);
        for (int i = 0; entero.length() < 2; i++) {
            entero = "0" + entero;
        }
        for (int i = 0; decimal.length() < 3; i++) {
            decimal += "0";
        }

        return entero + "," + decimal;
    }

    private synchronized void setTiempo() {
        this.tiempo = System.currentTimeMillis();
    }

    private synchronized boolean isTimeOut() {
        return (System.currentTimeMillis() - tiempo) > 5000;

    }
}
