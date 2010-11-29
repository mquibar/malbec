/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package screens.models.combos;

import entidades.Localidad;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class LocalidadComboModel extends AbstractComboModels<Localidad>{

    public LocalidadComboModel(List<Localidad> _lista) {
        super(_lista);
        for (Localidad localidad : _lista) {
            super.addElement(localidad.getNombre());
        }
    }

}
