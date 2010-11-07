/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author desarrollo
 */
public abstract class Intermediario<E> {

    protected String _clase;

    public Intermediario(String _clase) {
        this._clase = _clase;
    }

    public List<E> findAll() {
        try {
            Query q = ConectionAdmin.getInstance().getManager().createNamedQuery(_clase + ".findAll");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public boolean guardar(E obj) {
        try {
            ConectionAdmin.getInstance().getManager().persist(obj);
            return true;
        } catch (Exception ex) {
            System.out.println("************* <Error al guardar>");
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            System.out.println("<\\Error> *************");
            return false;
        }
    }

    public boolean actualizar(E obj) {
        try {
            ConectionAdmin.getInstance().getManager().merge(obj);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public abstract List<E> findInOrden(String orden);

    public abstract List<E> findByDto(Object dto);

    protected Query crearQuery(Map<String, Object> restricciones) {
        String sql = "SELECT o FROM " + _clase + " o ";
        
        if (restricciones.isEmpty()) {
            return ConectionAdmin.getInstance().getManager().createQuery(sql);
        }
        Query q = null;
        sql += "WHERE ";
        Iterator<String> i = restricciones.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            sql += "o." + key + "= :" + key + " AND ";
        }
        sql = sql.substring(0, sql.length()-5);
        q = ConectionAdmin.getInstance().getManager().createQuery(sql);

        i = restricciones.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next();
            q.setParameter(key, restricciones.get(key));
        }
        return q;
    }
}
