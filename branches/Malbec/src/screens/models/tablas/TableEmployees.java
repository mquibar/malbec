/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.tablas;

import entidades.Empleado;
import java.util.List;

/**
 *
 * @author desarrollo
 */
public class TableEmployees extends ModelSystemTable<Empleado> {

    public TableEmployees(List listRow) {
        super(listRow, "Código", "Apellido", "Nombre", "Dni");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        try{
            switch(columnIndex){
                case 0:
                    return _listRow.get(rowIndex).getCodigo();
                case 1:
                    return _listRow.get(rowIndex).getApellido();
                case 2:
                    return _listRow.get(rowIndex).getNombre();
                case 3:
                    return _listRow.get(rowIndex).getDni();
                default:
                    return "-";
            }
        }catch(Exception e){
            return "-";

        }

    }

}
