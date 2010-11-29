/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.parcelaTipoUva;

import entidades.Parcela;
import java.util.List;
import persistencia.Criterio;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpConsultarParcela {

    /**
     * Devuelve todas las parcelas de la finca
     *
     * @return, lista de las parcelas o null
     */
    public List<Parcela> consultarParcelas() {

        return Facade.getInstance().findAll(Parcela.class);
    }

    /**
     * Consulta las parcelas por codigo
     * 
     * @param codigo: codigo de la parcela a buscar
     * @return, la parcela encontrada o null
     */
    public Parcela consultarParcelaPorCodigo (String codigo) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("codigo");
        criterio.setOperador("=");
        criterio.setValor(codigo.toUpperCase());

        try {
            return (Parcela) Facade.getInstance().findByCriterio(Parcela.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Consulta las parcelas por el nombre
     *
     * @param nombre: nombre de la parcela a buscar
     * @return, la parcela encontrada o null
     */
    public Parcela consultarParcelaPorNombre (String nombre) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("nombre");
        criterio.setOperador("=");
        criterio.setValor(nombre.toUpperCase());

        try {
            return (Parcela) Facade.getInstance().findByCriterio(Parcela.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }
    }
  
}
