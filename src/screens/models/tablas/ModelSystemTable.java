/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screens.models.tablas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Manuel
 */
public abstract class ModelSystemTable<E> extends AbstractTableModel {

    protected String[] columnsName;

    public ModelSystemTable(List listRow, String... columnsName) {
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
        if (_listRow == null || _listRow.size()==0) {
            return 0;
        }
        return _listRow.size();
    }

    @Override
    public String getColumnName(int column) {
        return columnsName[column];
    }

    public E getSelectedItem(int rowIndex) {
        if (_listRow == null || _listRow.size()==0) {
            return null;
        }
        return _listRow.get(rowIndex);
    }

    public List<E> getListRow() {
        return _listRow;
    }

    public void addRow(E e){
        if(_listRow == null)
            _listRow = new ArrayList<E>();
        _listRow.add(e);
    }

    public void addRowAll(List<E> e){
        if(_listRow == null)
            _listRow = new ArrayList<E>();
        _listRow.addAll(e);
    }

    public void removeRow(E e){
        if(_listRow == null)
            _listRow = new ArrayList<E>();
        _listRow.remove(e);
    }

    public void removeRowAll(List<E> e){
        if(_listRow == null)
            _listRow = new ArrayList<E>();
        _listRow.removeAll(e);
    }

    public void clear(){
        _listRow.clear();
    }
}
