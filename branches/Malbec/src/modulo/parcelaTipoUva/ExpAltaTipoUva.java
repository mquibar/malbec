/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.Parcela;
import entidades.TipoUva;
import java.util.List;
import persistencia.Facade;
import systemException.DuplicateDataException;

/**
 *
 * @author Juan
 */
public class ExpAltaTipoUva {

    /**
     * Lista las parcelas para iniciar el alta del tipo de uva
     * @return una lista de todas las parcelas del la finca
     */
    public List <Parcela> iniciarAltaTipoUva () {

        return (new ExpConsultarParcela()).consultarParcelas();

    }


    /**
     * Guarda los Tipos de Uva
     *
     * @param parametros: List parcelas, Nombre, Codigo, Descripcion
     */
    public void guardarTipoUva (List<Parcela> parcela, String... parametros) throws Exception {

        TipoUva tipoUva = new TipoUva();

        if ((new ExpConsultarTipoUva()).consultarTipoUvaPorNombre(parametros[0].toUpperCase()) != null) {
            throw new DuplicateDataException("Empleado", parametros[0]);
        }

        tipoUva.setNombre(parametros[0].toUpperCase());
        tipoUva.setCodigo(parametros[1].toUpperCase());
        tipoUva.setDescripcion(parametros[2].toUpperCase());
        tipoUva.setListaParcelas(parcela);

        Facade.getInstance().beginTx();
        Facade.getInstance().guardar(tipoUva);
        Facade.getInstance().commitTx();

    }
}
