/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author desarrollo
 */
public class OpenFile {

    public static Properties openProperties(InputStream io) {
        try {
            Properties propierties = new Properties();
            propierties.load(io);
            return propierties;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public static Properties openProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(OpenFile.class.getResourceAsStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

    public static InputStream openInputStream(String path) {
        InputStream io=null;
        try{
        io = OpenFile.class.getResourceAsStream(path);
        }catch(Exception ex){
            System.err.println("mensaje"+ex.getMessage());
        }
        return io;
    }

    public static Properties openProperties(File file){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(file));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return properties;
    }
}
