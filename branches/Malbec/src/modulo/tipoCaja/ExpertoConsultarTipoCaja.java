/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.tipoCaja;

import entidades.TipoCaja;
import java.util.List;
import persistencia.Criterio;
import persistencia.Facade;

/**
 *
 * @author Manuel
 */
public class ExpertoConsultarTipoCaja {


    /**
     * Consulta todos los tipos de caja del sistema
     *
     * @return, lista con todos los tipos de caja del sistema
     */
    public List<TipoCaja> consultarTipoCaja () {

        return  Facade.getInstance().findAll(TipoCaja.class);

    }

    /**
     * Consulta el tipo de caja por el peso llena, existe un tipo por peso
     *
     * @param peso
     * @return
     */
    public TipoCaja consultarTipoCajaPorPesoLLena (double peso) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("pesoLleno");
        criterio.setOperador("=");
        criterio.setValor(peso);

        try {
            return (TipoCaja) Facade.getInstance().findByCriterio(TipoCaja.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }
    }



}
