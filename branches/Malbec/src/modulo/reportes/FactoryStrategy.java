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
    private static FactoryStrategy _instance;
    private FactoryStrategy(){

    }

    public static FactoryStrategy getInstance(){
        if(_instance == null)
            _instance = new FactoryStrategy();
        return _instance;
    }

    public static enum Exportacion{
        XLS, PDF, PDFVIEW
    }

    /**
     * devuelve la estrategia especifica para cada caso
     * 
     * @param exportacion: tipo de exportacion
     * @return devuleve la estrategia especifica
     */
    public ExportStrategy getStrategy(Exportacion exportacion){
        switch (exportacion) {

            case PDFVIEW:
                return new StrategyPdfView();
            case XLS:
                return new StrategyXls();
            default:
                return null;

        }

    }
}
