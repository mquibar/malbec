/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author Manuel
 */
public class CriterioCompuesto extends Criterio {

    private Criterio criterioA;
    private Criterio criterioB;
    
    

    public Criterio getCriterioA() {
        return criterioA;
    }

    public void setCriterioA(Criterio criterioA) {
        this.criterioA = criterioA;
    }

    public Criterio getCriterioB() {
        return criterioB;
    }

    public void setCriterioB(Criterio criterioB) {
        this.criterioB = criterioB;
    }
}
