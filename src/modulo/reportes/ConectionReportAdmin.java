/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modulo.reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Juan
 */
public class ConectionReportAdmin {

    public static Connection getConnection () {

        Properties prop = tools.OpenFile.openProperties("/malbec/ConectionProperties.properties");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("pass");
        String url = prop.getProperty("url");
        String driver = prop.getProperty("driver");
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            return null;
        }
    }

}
