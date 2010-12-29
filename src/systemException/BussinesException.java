/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemException;

/**
 *
 * @author Juan
 */
public class BussinesException extends SystemException {

    private String operacion;
     public BussinesException(String operacion) {
        super(4, "Se produjo un error al realizar la operación [" + operacion +"]");
        this.operacion = operacion;
    }



}