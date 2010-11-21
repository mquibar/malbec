/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.tablas;

import entidades.TipoUva;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class TableTipoUva extends ModelSystemTable<TipoUva> {

    public Object getValueAt(int rowIndex, int columnIndex) {
        if(_listRow==null)
            return "-";
        switch (columnIndex){
            case 0: return _listRow.get(rowIndex).getCodigo();
            case 1: return _listRow.get(rowIndex).getNombre();
            default: return "-";
        }
    }

    public TableTipoUva( List<TipoUva> listRow) {
        super( listRow ,"Código", "Nombre");
    }

}
