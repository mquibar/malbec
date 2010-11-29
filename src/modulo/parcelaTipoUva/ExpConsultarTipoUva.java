/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.TipoUva;
import java.util.List;
import persistencia.Criterio;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpConsultarTipoUva {

    /**
     * Lista todos los tipos de uva del sistema
     * 
     * @return, lista de los tipos de uva o null
     */
    public List<TipoUva> consultarTiposDeUva () {
    
        return Facade.getInstance().findAll(TipoUva.class);
        
    }


    /**
     * Busca el tipo de uva por su codigo
     *
     * @param codigo: codigo del tipo de uva a ser bucada
     * @return, tipo de uva encontrada o null
     */
    public TipoUva consultarTipoUvaPorCodigo (String codigo) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("codigo");
        criterio.setOperador("=");
        criterio.setValor(codigo.toUpperCase());

        try {
            return (TipoUva) Facade.getInstance().findByCriterio(TipoUva.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Busca el tipo de uva por su nombre
     *
     * @param nombre: nombre del tipo de uva a ser bucada
     * @return, tipo de uva encontrada o null
     */
    public TipoUva consultarTipoUvaPorNombre (String nombre) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("nombre");
        criterio.setOperador("=");
        criterio.setValor(nombre.toUpperCase());

        try {
            return (TipoUva) Facade.getInstance().findByCriterio(TipoUva.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }

    }

}
