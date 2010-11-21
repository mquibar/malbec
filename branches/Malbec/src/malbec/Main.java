/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package malbec;

import modulo.leerPeso.ExpertoLeerPeso;


/**
 *
 * @author Manuel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new screens.LectorPeso();

        double peso = (new ExpertoLeerPeso()).leerPeso("peso");
        System.out.println("Peso: "+peso);
        

    }

}
