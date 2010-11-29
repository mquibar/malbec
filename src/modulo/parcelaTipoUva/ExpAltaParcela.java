/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.Parcela;
import entidades.TipoUva;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpAltaParcela {

    /**
     *  guarda la parcela segun los datos enviados como parametros
     *
     * @param parametros: Nombre, Codigo, Descripcion
     */
    public void guardarParcela (String... parametros) throws Exception{

        Parcela parcela = new Parcela();

        parcela.setNombre(parametros[0].toUpperCase());
        parcela.setCodigo(parametros[1].toUpperCase());
        parcela.setDescripcion(parametros[2].toUpperCase());

        Facade.getInstance().beginTx();
        Facade.getInstance().guardar(parcela);
        Facade.getInstance().commitTx();

    }
    

}
