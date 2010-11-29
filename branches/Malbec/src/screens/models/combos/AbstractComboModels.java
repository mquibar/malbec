/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.combos;

import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Manuel
 */
public abstract class AbstractComboModels<E> extends DefaultComboBoxModel {

    protected List<E> _lista= null;

    public AbstractComboModels(List<E> _lista) {
        this._lista = _lista;
        super.addElement("Seleccione un Elemento");
    }

    public E getSelected() {
        int index = super.getIndexOf(super.getSelectedItem());
        if(_lista == null ||index == 0)
            return null;
        return _lista.get(index-1);
    }


}
