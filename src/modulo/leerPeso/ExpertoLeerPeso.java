/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.leerPeso;

import driver.driverbalanza.driverBalanza;
import entidades.Caja;
import entidades.Parcela;
import entidades.TipoUva;
import java.util.List;
import modulo.consultas.ExpertoConsultaTipoUvayParcela;

/**
 *
 * @author Juan
 */
public class ExpertoLeerPeso {
    
    private TipoUva _tipoUva;
    private Parcela _parcela;
    private int reintentos  = 3;

    /**
     * Lista todos los tipos de uva que la finca posee
     *
     * @return List<TipoUva> devuelve un listado de todos los tipos de uva
     */
    public List<TipoUva> listarTipoUva () {
        return (new ExpertoConsultaTipoUvayParcela()).listarTiposDeUvas();
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
        return (new ExpertoConsultaTipoUvayParcela()).listarParcelaPorTipo(tipo);
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
    public double leerPeso (String codigo) {
        //Empleado empleado = (new IntermediarioEmpleado ()).consultarEmpleadoPorCodigo(codigo);
        //Caja caja = new Caja();
        try {

        double peso = (new driverBalanza(reintentos)).getPeso();
            if (verificarPeso (peso)) {
            //caja.setFechaEmpaque(new Date());
            //caja.setParcela(_parcela);
            //caja.setTipoUva(_tipoUva);
            //caja.setEmpleado(empleado);
            //caja.setPeso(peso);
            //guardarCaja (caja);
            return peso;
            }

        } catch (Exception ex) {
            System.out.println("Exception: "+ex.getMessage());
        }

        return 0.0;
    }

    /**
     * Guarda la caja que se encuentra en condiciones
     *
     * @param caja: caja a ser guardada
     */
    private void guardarCaja (Caja caja) {

        //(new IntermediarioCaja()).guardar(caja);

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
