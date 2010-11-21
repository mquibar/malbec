/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.tablas;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel
 */
public abstract class ModelSystemTable<E> extends AbstractTableModel {

    protected String[] columnsName;

    public ModelSystemTable( List listRow,String ... columnsName) {
        this.columnsName = columnsName;
        this._listRow = listRow;
    }
    protected List<E> _listRow;

    public void setListRow(List<E> listRow) {
        this._listRow = listRow;
    }


    public int getColumnCount() {
        return columnsName.length;
    }

    public int getRowCount() {
        if(_listRow==null)
            return 0;
        return _listRow.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnsName[column];
    }


}
