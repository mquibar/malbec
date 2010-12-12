/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.Parcela;
import java.util.List;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpModificarParcela {

    /**
     * Inicia la modificacion de la parcela listando todas las parcelas del sistema
     *
     * @return
     */
    public List <Parcela> iniciarModificacionParcela () {

        return (new ExpConsultarParcela()).consultarParcelas();

    }

    /**
     *  modifica la parcela segun los datos enviados como parametros
     *
     * @param Parcela a modificar, parametros: Nombre, Codigo, Descripcion
     */
    public void actualizar (Parcela parcela, String... parametros) throws Exception {

        parcela.setNombre(parametros[0].toUpperCase());
        parcela.setCodigo(parametros[1].toUpperCase());
        parcela.setDescripcion(parametros[2].toUpperCase());

        Facade.getInstance().beginTx();
        Facade.getInstance().actualizar(parcela);
        Facade.getInstance().commitTx();

    }

}
