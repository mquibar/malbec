/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.tablas;

import entidades.Parcela;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class TableParcela extends ModelSystemTable<Parcela> {

    public TableParcela(List listRow) {
        super(listRow, "Nombre");
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_listRow == null)
            return "-";
        switch (columnIndex){
            case 0:
                return _listRow.get(rowIndex).getNombre();
            default:
                return "-";
        }
    }

}
