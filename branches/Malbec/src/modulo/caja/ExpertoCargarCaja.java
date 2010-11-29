/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.caja;

import driver.driverbalanza.driverBalanza;
import entidades.Caja;
import entidades.Empleado;
import entidades.Parcela;
import entidades.TipoUva;
import java.util.Date;
import java.util.List;
import modulo.empleado.ExpConsultarEmpleado;
import modulo.parcelaTipoUva.ExpConsultarTipoUva;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpertoCargarCaja {
    
    private TipoUva _tipoUva;
    private Parcela _parcela;
    private int reintentos  = 3;

    /**
     * Lista todos los tipos de uva que la finca posee
     *
     * @return List<TipoUva> devuelve un listado de todos los tipos de uva
     */
    public List<TipoUva> listarTipoUva () {
        return (new ExpConsultarTipoUva()).consultarTiposDeUva();
    }

    /**
     * Lista todas las parcelas donde se encuentra el tipo de uva que se le
     * envia como parametro
     *
     * @param tipo: tipo de uva seleccionada por el usuario
     * @return List<Parcela> lista las parcelas que contienen esa tipo de uva
     */
    public List<Parcela> listarParcelaDelTipo (TipoUva tipo) {
        _tipoUva = tipo;
        return tipo.getListaParcelas();
    }

    /**
     * Asigna la parcela indicada con la cual se trabajará ese día
     *
     * @param parcela: parcela seleccionada por el usuario
     * @return true si la parcela es válida y se asgino correctamente
     */
    public boolean asignarParcela (Parcela parcela) {
        _parcela = parcela;
        return true;
    }

    /**
     * Lee el  peso de una caja, asociada a un tipo de uva, parcela y empleado
     *
     * @param codigo: Codigo del empleado
     * @return: el peso leido por la balanza
     */
    public double cargarCaja (String codigo) {
        Empleado empleado = (new ExpConsultarEmpleado()).consultarEmpleadoPorCodigo(codigo);
        Caja caja = new Caja();
        try {

        double peso = (new driverBalanza(reintentos)).getPeso();
            if (verificarPeso (peso)) {
                caja.setFechaEmpaque(new Date());
                caja.setParcela(_parcela);
                caja.setTipoUva(_tipoUva);
                caja.setEmpleado(empleado);
                caja.setPeso(peso);
                Facade.getInstance().beginTx();
                Facade.getInstance().guardar(caja);
                Facade.getInstance().commitTx();
                return peso;
            }

        } catch (Exception ex) {
            System.out.println("Exception: "+ex.getMessage());
        }

        return 0.0;
    }

    /**
     * Verifica que el peso este entre los limites indicados
     *
     * @param peso: peso a verificar
     * @return
     */
    private boolean verificarPeso (double peso) {

        double limitesuperior = 0.500;
        double limiteinferior = 0.300;

        if (peso < limitesuperior && peso > limiteinferior) {
            return true;
        } else {
            return false;
        }
    }

}
