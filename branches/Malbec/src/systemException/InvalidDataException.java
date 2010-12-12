/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package systemException;

/**
 *
 * @author Juan
 */
public class InvalidDataException extends SystemException {

    String atributo;
    Object valor;

    public InvalidDataException(String atributo, Object valor) {
        super(3, "Datos invalidos: " + atributo.toUpperCase() +" = "+ valor);
        this.atributo= atributo;
        this.valor=valor;
    }

    public String getAtributo() {
        return atributo;
    }

    public Object getValor() {
        return valor;
    }

    

}
