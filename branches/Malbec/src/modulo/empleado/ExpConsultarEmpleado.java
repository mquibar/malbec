/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.empleado;

import entidades.Empleado;
import java.util.List;
import persistencia.Criterio;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpConsultarEmpleado {

    /**
     * Lista los empleados que no han sido eliminados del sistema
     *
     * @return, lista de los empleado no eliminados o null
     */
    public List <Empleado> consultarEmpleadosNoEliminados () {
        Criterio criterio = new Criterio();
        criterio.setAtributo("eliminado");
        criterio.setOperador("!=");
        criterio.setValor(false);

        try {
            return (List<Empleado>) Facade.getInstance().findByCriterio(Empleado.class, criterio);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Busca los empleados por el numero de documento
     *
     * @param dni, numero de documento del empleado a buscar
     * @return, empleado encontrado o null
     */
    public Empleado consultarEmpleadoPorDni (String dni) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("dni");
        criterio.setOperador("=");
        criterio.setValor(dni.toUpperCase());

        try {
            return (Empleado) Facade.getInstance().findByCriterio(Empleado.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Busca los empleados por el numero de codigo
     *
     * @param codigo, codigo del empleado a buscar
     * @return, empleado encontrado o null
     */
    public Empleado consultarEmpleadoPorCodigo (String codigo) {
        Criterio criterio = new Criterio();
        criterio.setAtributo("codigo");
        criterio.setOperador("=");
        criterio.setValor(codigo.toUpperCase());

        try {
            return (Empleado) Facade.getInstance().findByCriterio(Empleado.class, criterio).get(0);
        } catch (Exception e) {
            return null;
        }

    }

}
