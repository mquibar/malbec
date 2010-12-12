/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.tipoCaja;

import entidades.ComponenteConservante;
import entidades.TipoCaja;
import java.util.List;
import persistencia.Facade;

/**
 *
 * @author Manuel
 */
public class ExpertoModificarTipoCaja {

    /**
     * Inicia la modificacion de un tipo de caja
     *
     * @return, retorna todos los tipos de caja asociada
     */
    public List<TipoCaja> iniciarModificacionTipoCaja () {

        return (new ExpertoConsultarTipoCaja()).consultarTipoCaja();
    }

    /**
     * modifica un determinado tipo de caja seleccionado
     *
     * @param tipo, el tipo de caja a modificar
     * @param peso, el nuevo peso lleno que va a contener
     * @param componentes, nuevos componentes pertenecientes al tipo
     * @param parametros, Nombre, descripcion
     * @throws Exception
     */
    public void actualizar (TipoCaja tipo, double peso, List<ComponenteConservante> componentes, String...parametros ) throws Exception {

        tipo.setPesoLleno(peso);
        tipo.setComponentes(componentes);
        tipo.setNombreTipo(parametros[0].toUpperCase());
        tipo.setDescripcion(parametros[1].toUpperCase());

        Facade.getInstance().beginTx();
        Facade.getInstance().actualizar(tipo);
        Facade.getInstance().commitTx();


    }

}
