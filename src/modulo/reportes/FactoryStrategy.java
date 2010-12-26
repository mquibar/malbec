/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

/**
 *
 * @author MARIANO
 */
public class FactoryStrategy {
    private FactoryStrategy _instance;
    private FactoryStrategy(){

    }

    public FactoryStrategy getInstance(){
        if(_instance == null)
            _instance = new FactoryStrategy();
        return _instance;
    }

    public static enum Exportacion{
        XLS, PDF, PDFVIEW
    }
    public ExportStrategy getStrategy(Exportacion exportacion){
        return null;

    }
}
