/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.tablas;

import entidades.TipoCaja;
import java.util.List;

/**
 *
 * @author Juan
 */
public class TableTipoCaja extends ModelSystemTable<TipoCaja> {

    public TableTipoCaja(List list) {
        super(list, "Nombre", "Peso Llena");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
       if(_listRow == null)
            return "-";
        switch (columnIndex){
            case 0:
                return _listRow.get(rowIndex).getNombreTipo();
            case 1:
                return _listRow.get(rowIndex).getPesoLleno();
            default:
                return "-";
        }
    }


}
