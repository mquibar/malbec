/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.tipoCaja;

import entidades.ComponenteConservante;
import entidades.TipoCaja;
import java.util.List;
import persistencia.Facade;
import systemException.DuplicateDataException;

/**
 *
 * @author Manuel
 */
public class ExpertoAltaTipoCaja {

    /**
     * guarda un tipo de caja determinado
     *
     * @peso el peso que va a contener el tipo de caja
     * @componentes componentes agregados a la caja
     * @param parametros: nombre, descripcion
     * @throws Exception
     */
    public void guardar (double peso, List <ComponenteConservante> componentes, String... parametros) throws Exception {

        TipoCaja tipo = new TipoCaja();

        if ((new ExpertoConsultarTipoCaja()).consultarTipoCajaPorPesoLLena(peso) != null) {
            throw new DuplicateDataException("Tipo de Caja", String.valueOf(peso));
        }

        tipo.setPesoLleno(peso);
        tipo.setComponentes(componentes);
        tipo.setNombreTipo(parametros[0].toUpperCase());
        tipo.setDescripcion(parametros[1].toUpperCase());

        Facade.getInstance().beginTx();
        Facade.getInstance().guardar(tipo);
        Facade.getInstance().commitTx();

    }

}
