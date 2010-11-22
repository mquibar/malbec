/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel
 */
public class Criterio {

    private String atributo;
    protected String operador;
    private Object valor;

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "o."+atributo + operador + ":"+atributo;
    }

    public Map<String,Object> toMap(){
        Map mapa = new HashMap<String, Object>();
        mapa.put(atributo, valor);
        return mapa;
    }

}
