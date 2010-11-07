/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import entidades.Parcela;
import java.util.List;

/**
 *
 * @author Manuel
 */
public class IntermediarioParcela extends Intermediario<Parcela>{

    public IntermediarioParcela() {
        super("Parcela");
    }


    @Override
    public List<Parcela> findInOrden(String orden) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Parcela> findByDto(Object dto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
