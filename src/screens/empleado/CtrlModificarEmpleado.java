/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.empleado;

import entidades.Empleado;
import entidades.Localidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modulo.empleado.ExpModificarEmpleado;
import screens.ctrlMainMenu;
import screens.models.combos.LocalidadComboModel;

/**
 *
 * @author Juan
 */
public class CtrlModificarEmpleado {

    private Empleado empleado;
    private AltaEmpleado _pantalla;
    private ExpModificarEmpleado _gestorModificar;
    private LocalidadComboModel _cLoalidad;
    private ctrlListadoPersonal _control;

    public CtrlModificarEmpleado(Empleado empleado, ctrlListadoPersonal control) {
        this.empleado = empleado;
        _control = control;
        _gestorModificar = new ExpModificarEmpleado();
        _pantalla = new AltaEmpleado();
        loadScreen();
    }

    private void loadScreen(){
        _cLoalidad = new LocalidadComboModel(_gestorModificar.listarLocalidades());
        _pantalla.getCbxLocalidad().setModel(_cLoalidad);

        _pantalla.getTxtApellido().setText(empleado.getApellido());
        _pantalla.getTxtNombre().setText(empleado.getNombre());
        _pantalla.getTxtDni().setText(empleado.getDni());
        _pantalla.getTxtFecNac().setDate(empleado.getFechaNacimiento());

        _pantalla.getTxtCodigo().setText(empleado.getCodigo());

        _pantalla.getCbxLocalidad().setSelectedIndex(_cLoalidad.getIndexOf(empleado.getDomicilio().getLocalidad()));
        _pantalla.getTxtCalle().setText(empleado.getDomicilio().getCalle());
        _pantalla.getTxtNumero().setText(empleado.getDomicilio().getNumero());
        _pantalla.getTxtPiso().setText(empleado.getDomicilio().getPisto());
        _pantalla.getTxtDepto().setText(empleado.getDomicilio().getDepto());

        _pantalla.getBtnCancel().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressCancelButton();
            }
        });
        _pantalla.getBtnSave().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pressOkButton();
            }
        });
        _pantalla.setVisible(true);
        ctrlMainMenu.getDestktopPane().add(_pantalla);
        _pantalla.toFront();
    }

    void pressCancelButton(){
        if(JOptionPane.showConfirmDialog(_pantalla, "Desea cancela la operación?", "Cancelar", JOptionPane.YES_NO_OPTION)==0){
            _pantalla.dispose();
            _control.habilitar();
        }

    }

    void pressOkButton(){
        String[] parametros= new String[9];
        Localidad localidad = ((LocalidadComboModel) _pantalla.getCbxLocalidad().getModel()).getSelected();

        parametros[0]=_pantalla.getTxtApellido().getText();
        parametros[1]=_pantalla.getTxtNombre().getText();
        parametros[2]=_pantalla.getTxtDni().getText();
        parametros[3]=tools.ManejaFechas.convertirDate(_pantalla.getTxtFecNac().getDate());
        parametros[4]=_pantalla.getTxtCodigo().getText();
        parametros[5]=_pantalla.getTxtCalle().getText();
        parametros[6]=_pantalla.getTxtNumero().getText();
        parametros[7]=_pantalla.getTxtPiso().getText();
        parametros[8]=_pantalla.getTxtDepto().getText();

        for (String string : parametros) {
            if(string.isEmpty()){
                JOptionPane.showMessageDialog(_pantalla, "Debe completar todos los campos", "Error de carga", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        try {
            _gestorModificar.actualizar(empleado, localidad, parametros);
            JOptionPane.showMessageDialog(_pantalla, "Operación realizada con exito","Guardado",JOptionPane.INFORMATION_MESSAGE);
            _pantalla.dispose();
            _control.habilitar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_pantalla, "No se pudo completar la operación.\nError: "+ ex.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
    }
}
