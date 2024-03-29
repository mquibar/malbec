/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import persistencia.exceptions.PersistException;

/**
 *
 * @author desarrollo
 */
class Intermediario<E> {

    protected String _clase;

    public Intermediario(String _clase) {
        this._clase = _clase;
    }

    public List<E> findAll() {
        try {
            Query q = ConectionAdmin.getInstance().getManager().createQuery("SELECT c FROM "+_clase + " c");
            return q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public void guardar(E obj) throws PersistException {
        try {
            ConectionAdmin.getInstance().getManager().persist(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistException("Guardar", obj);
        }
    }

    public void actualizar(E obj) throws PersistException {
        try {
            ConectionAdmin.getInstance().getManager().merge(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistException("Actualizar", obj);
        }
    }

    public List<E> findInOrden(String orden) {
        Query q = ConectionAdmin.getInstance().getManager().createQuery("SELECT o FROM " + _clase + " o ORDER BY o." + orden);
        return q.getResultList();
    }

    public List<E> findByCriterio(Criterio criterio) {
        String sql = "SELECT o FROM " + _clase + " o WHERE " + criterio;
        Query q = ConectionAdmin.getInstance().getManager().createQuery(sql);
        for (Object[] criteria : criterio.toParameter()) {
            q.setParameter(String.valueOf(criteria[0]), criteria[1]);
        }

        return q.getResultList();
    }

    public List<E> excecuteQuery(String query){
        return ConectionAdmin.getInstance().getManager().createQuery(query).getResultList();
    }
}
