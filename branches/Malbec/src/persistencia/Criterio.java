/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Manuel
 */
public class Criterio {

    private String atributo;
    protected String operador;
    private Object valor;
    private static int gui=0;
    private String parametro;


    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
        parametro = atributo +gui++;
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

        return "o."+atributo + operador + ":"+parametro;
    }

    public Map<String,Object> toMap(){
        Map mapa = new HashMap<String, Object>();
        mapa.put(parametro, valor);
        return mapa;
    }

    public List<Object[]> toParameter(){
        List<Object[]> sql = new ArrayList<Object[]>();
        Object[] o = new Object[2];
        o[0]=parametro;
        o[1]=valor;
        sql.add(o);
        
        return sql;
    }

}
