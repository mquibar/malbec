/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.empleado;

import entidades.Empleado;
import entidades.Localidad;
import java.util.List;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpModificarEmpleado {

    /**
     * Inicia la modificacion del empleado listando todos los que no estan eliminados
     *
     * @return, lista de los empleados no eliminados o null
     */
    public List <Empleado> iniciarModificacionEmpleado () {
        return (new ExpConsultarEmpleado()).consultarEmpleadosNoEliminados();
    }

    public List<Localidad> listarLocalidades(){
        return  Facade.getInstance().findAll(Localidad.class);
    }

    /**
     * Modifica los datos personales del empleado
     *
     * @param empleado, empleado a modificar
     * @param localidad
     * @param parametros, Apellido, Nombre, DNI, Fecha de Nacimiento, Codigo, Calle, Numero, Piso, Departamento
     * @throws Exception, modificacion exitosa o no
     */
    public void actualizar (Empleado empleado, Localidad localidad, String... parametros) throws Exception {
        
        empleado.setApellido(parametros[0].toUpperCase());
        empleado.setNombre(parametros[1].toUpperCase());
        empleado.setDni(parametros[2].toUpperCase());
        empleado.setFechaNacimiento(tools.ManejaFechas.convertirString(parametros[3]));
        empleado.setCodigo(parametros[4].toUpperCase());
        empleado.getDomicilio().setCalle(parametros[5].toUpperCase());
        empleado.getDomicilio().setNumero(parametros[6].toUpperCase());
        empleado.getDomicilio().setPisto(parametros[7].toUpperCase());
        empleado.getDomicilio().setDepto(parametros[8].toUpperCase());
        empleado.getDomicilio().setLocalidad(localidad);

        Facade.getInstance().beginTx();
        Facade.getInstance().actualizar(empleado);
        Facade.getInstance().commitTx();
        
    }

    /**
     * Le da de baja al empleado seleccionado
     * 
     * @param empleado, empleado a dar  de baja del sistema
     * @throws Exception, baja exitosa o no
     */
    public void baja (Empleado empleado) throws Exception {

        empleado.setEliminado(true);

        Facade.getInstance().beginTx();
        Facade.getInstance().actualizar(empleado);
        Facade.getInstance().commitTx();


    }

}
