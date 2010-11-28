/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.empleado;

import entidades.Domicilio;
import entidades.Empleado;
import entidades.Localidad;
import java.util.Date;
import java.util.List;
import persistencia.Facade;

/**
 *
 * @author Juan
 */
public class ExpAltaEmpleado {

    public List <Localidad> iniciarAltaEmpleado () {
        return Facade.getInstance().findAll(Localidad.class);
    }

    /**
     * Guarda un empleado nuevo
     *
     * @param localidad
     * @param parametros: Apellido, Nombre, DNI, Fecha de Nacimiento, Codigo, Calle, Numero, Piso, Departamento
     */
    public void guardar (Localidad localidad, String... parametros) throws Exception {

        Empleado empleado = new Empleado();

        empleado.setApellido(parametros[0].toUpperCase());
        empleado.setNombre(parametros[1].toUpperCase());
        empleado.setDni(parametros[2].toUpperCase());
        empleado.setFechaNacimiento(tools.ManejaFechas.convertirString(parametros[3]));
        empleado.setCodigo(parametros[4].toUpperCase());
        empleado.setFechaIngreso(new Date());
        empleado.setEliminado(false);
        empleado.setDomicilio(new Domicilio());
        empleado.getDomicilio().setCalle(parametros[5].toUpperCase());
        empleado.getDomicilio().setNumero(parametros[6].toUpperCase());
        empleado.getDomicilio().setPisto(parametros[7].toUpperCase());
        empleado.getDomicilio().setDepto(parametros[8].toUpperCase());
        empleado.getDomicilio().setLocalidad(localidad);


        Facade.getInstance().beginTx();
        Facade.getInstance().guardar(empleado);
        Facade.getInstance().commitTx();


    }

}
