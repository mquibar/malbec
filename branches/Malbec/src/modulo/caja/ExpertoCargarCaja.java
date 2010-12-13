/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modulo.caja;

import driver.driverbalanza.driverBalanza;
import entidades.Caja;
import entidades.Empleado;
import entidades.Parcela;
import entidades.TipoCaja;
import entidades.TipoUva;
import java.util.Date;
import java.util.List;
import modulo.empleado.ExpConsultarEmpleado;
import modulo.parcelaTipoUva.ExpConsultarTipoUva;
import modulo.tipoCaja.ExpertoConsultarTipoCaja;
import persistencia.Facade;
import systemException.InvalidDataException;

/**
 *
 * @author Juan
 */
public class ExpertoCargarCaja {

    private TipoUva _tipoUva;
    private Parcela _parcela;
    private int reintentos = 3;
    private Caja ultima = null;
    private TipoCaja _tipoCaja;

    /**
     * Lista todos los tipos de uva que la finca posee
     *
     * @return List<TipoUva> devuelve un listado de todos los tipos de uva
     */
    public List<TipoUva> listarTipoUva() {
        return (new ExpConsultarTipoUva()).consultarTiposDeUva();
    }

    /**
     * Lista todas las parcelas donde se encuentra el tipo de uva que se le
     * envia como parametro
     *
     * @param tipo: tipo de uva seleccionada por el usuario
     * @return List<Parcela> lista las parcelas que contienen esa tipo de uva
     */
    public List<Parcela> listarParcelaDelTipo(TipoUva tipo) {
        _tipoUva = tipo;
        return tipo.getListaParcelas();
    }

    /**
     * lista los tipos de caja del sistema, para iniciar el dia
     *
     * @return List <TipoCaja> listado de tipos de caja
     */
    public List<TipoCaja> listarTipoCaja () {

        return (new ExpertoConsultarTipoCaja()).consultarTipoCaja();
    }

    /**
     * Asigna la parcela indicada con la cual se trabajará ese día
     *
     * @param parcela: parcela seleccionada por el usuario
     * @return true si la parcela es válida y se asgino correctamente
     */
    public boolean asignarParcela(Parcela parcela) {
        _parcela = parcela;
        return true;
    }

    public void asignarTipoCaja(TipoCaja tipoCaja){
        _tipoCaja= tipoCaja;
    }

    /**
     * Lee el  peso de una caja, asociada a un tipo de uva, parcela y empleado
     *
     * @param codigo: Codigo del empleado
     * @return: el peso leido por la balanza
     */
    public double cargarCaja(String codigo) throws InvalidDataException {
        Empleado empleado = (new ExpConsultarEmpleado()).consultarEmpleadoPorCodigo(codigo);
        if (empleado == null) {
            throw new InvalidDataException("empleado", 0.0);
        }
        Caja caja = new Caja();
        double peso = 0;
        try {

            peso = (new driverBalanza(reintentos)).getPeso();
            verificarPeso(peso, _tipoCaja.getPesoLleno());
            caja.setFechaEmpaque(new Date());
            caja.setTipoCaja(_tipoCaja);
            caja.setParcela(_parcela);
            caja.setTipoUva(_tipoUva);
            caja.setEmpleado(empleado);
            caja.setPeso(peso);
            verificarCaja(caja);
            Facade.getInstance().beginTx();
            Facade.getInstance().guardar(caja);
            Facade.getInstance().commitTx();
            return peso;

        } catch (InvalidDataException ie) {
            throw new InvalidDataException(ie.getAtributo(), ie.getValor());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }

        return 0.0;
    }

    /**
     * Verifica que el peso este entre los limites indicados
     *
     * @param peso: peso a verificar
     * @param pesoLLena: peso segun el tipo de caja seleccionado
     * @return
     */
    private void verificarPeso(double peso, double pesoLLena) throws InvalidDataException {

        /*
         * Los valores que se le suman y se restan al peso llena, se tienen que leer del archivo
         * de parametros
         */
        double limitesuperior = pesoLLena + 0.500;
        double limiteinferior = pesoLLena - 0.400;

        if (!(peso < limitesuperior && peso > limiteinferior)) {
            throw new InvalidDataException("peso caja", peso);
        }
    }

    private void verificarCaja(Caja caja) throws InvalidDataException {
        if (ultima == null) {
            ultima = caja;
            return;
        }
        if (ultima.getEmpleado().equals(caja.getEmpleado()) && ultima.getPeso() == caja.getPeso()) {
            throw new InvalidDataException("Caja", ultima.getPeso());
        }
        ultima = caja;
    }
}
