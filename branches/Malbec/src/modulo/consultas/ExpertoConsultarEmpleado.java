/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.consultas;

import entidades.Empleado;
import java.util.List;

/**
 *
 * @author Juan
 */
public class ExpertoConsultarEmpleado {

    public Empleado consultarEmpleadoPorCodigo (String codigo) {
        //Empleado empleado = (new IntermediarioEmpleado()).consultarPorCodigo(codigo);
        return new Empleado();
    }

    public List<Empleado> listarEmpleados(){
        return persistencia.Facade.getInstance().findAll(Empleado.class);
    }
}
