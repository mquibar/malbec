/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.Parcela;
import entidades.TipoUva;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpModificarTipoUva {

    /**
     * Inicia la modificacion del tipo de uva, devolviendo todos los tipos de uva
     * y las pareclas.
     *
     * @return, hasMap que contiene todos los tipos de uva y las parcelas o null
     */
    public Map<String, List> iniciarModificacionTipoUva () {
        Map<String,List> listas = new HashMap<String, List>();
        listas.put("TIPOUVA", (new ExpConsultarTipoUva()).consultarTiposDeUva());
        listas.put("PARCELA", (new ExpConsultarParcela()).consultarParcelas());
        return listas;
    }

    /**
     * Actualiza los Tipos de Uva
     *
     * @param parametros: List parcelas, Nombre, Codigo, Descripcion: a actualizar
     */
    public void actualizar (TipoUva tipoUva, List<Parcela> parcela, String... parametros) throws Exception {

        tipoUva.setNombre(parametros[0].toUpperCase());
        tipoUva.setCodigo(parametros[1].toUpperCase());
        tipoUva.setDescripcion(parametros[2].toUpperCase());
        tipoUva.setListaParcelas(parcela);

        Facade.getInstance().beginTx();
        Facade.getInstance().actualizar(tipoUva);
        Facade.getInstance().commitTx();

    }

}
