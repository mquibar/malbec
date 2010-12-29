/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.logging.Log;

/**
 *
 * @author desarrollo
 */
class ConectionAdmin {

    //@PersistenceUnit
    private EntityManagerFactory _factory;
    private EntityManager _manager = null;
    private int _contador = 0;
    private static ConectionAdmin _instance = null;
    private Log _log;

    public static ConectionAdmin getInstance() {
        if (_instance == null) {
            _instance = new ConectionAdmin();
        }
        return _instance;
    }

    private ConectionAdmin() {
        Properties def = tools.OpenFile.openProperties("/malbec/ConectionProperties.properties");
        Properties prop = new Properties();
        prop.put("hibernate.connection.url", def.getProperty("url"));
        prop.put("hibernate.connection.driver_class",def.getProperty("driver"));
        prop.put("hibernate.connection.username", def.getProperty("user"));
        prop.put("hibernate.connection.password" , def.getProperty("pass"));
        System.out.println(prop.getProperty("hibernate.connection.url"));
        _factory = Persistence.createEntityManagerFactory("MalbecPU",prop);

    }

    public synchronized void beginTransaction() {
        if (_manager != null) {
            if (_manager.isOpen() && _contador > 0 && _manager.getTransaction().isActive()) {
                _contador++;
                return;
            }
        } else {
            getManager();
        }

        try {
            if (!_manager.isOpen()) {
                _manager = _factory.createEntityManager();
            }
            _manager.getTransaction().begin();
            _contador++;
        } catch (Exception ex) {
            ex.printStackTrace();
            _log.error(ex.getMessage());
        }
    }

    public synchronized void commitTransaction() throws Exception {
        if (_contador > 1) {
            _contador--;
            return;
        }
        if (_manager.isOpen() && _manager.getTransaction().isActive()) {
            try {
                _manager.getTransaction().commit();
            } catch (Exception ex) {
                ex.printStackTrace();
                _log.error(ex.getMessage());
                throw new Exception(ex);
            } finally {
                _contador = 0;
            }
        }
    }

    public synchronized void rollbackTransaction() {
        if (_contador > 1) {
            _contador--;
            return;
        }
        if (_manager.isOpen()) {
            try {
                _manager.getTransaction().rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                _log.error(ex.getMessage());
            } finally {
                _contador = 0;
            }
        }
    }

    public EntityManager getManager() {
        if (_manager == null) {
            _manager = _factory.createEntityManager();
        }
        return _manager;
    }

    public void closeConection(){
        if(_manager!=null)
            _manager.close();
    }
}
