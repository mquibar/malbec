/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.consultas;

import entidades.Parcela;
import entidades.TipoUva;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ExpertoConsultaTipoUvayParcela {

    public List<TipoUva> listarTiposDeUvas () {
        //ArrayList <TipoUva> = (new IntermediarioParcela()).listarTipo ();
        return new ArrayList<TipoUva> ();
    }

    public List<Parcela> listarParcelaPorTipo (TipoUva tipo) {
        //ArrayList <Parcela> = (new IntermediarioParcela()).listarParcelaporTipo (tipo);
        return new ArrayList<Parcela> ();
    }

}
