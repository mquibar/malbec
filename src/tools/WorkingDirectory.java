/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.net.URL;

/**
 *
 * @author desarrollo
 */
public class WorkingDirectory {

    private static File WORKING_DIRECTORY=null;
    private static String PATH=null;

    public static String getWorkingDirectoryPath() {
//        String Recurso = WorkingDirectory.class.getSimpleName() + ".class";
//        if (PATH == null) {
//            try {
//                URL url = WorkingDirectory.class.getResource(Recurso);
//
//                if (url.getProtocol().equals("file")) {
//                    File f = new File(url.toURI());
//                    PATH = f.getParentFile().getParentFile().getWorkingDirectoryPath().replaceAll("%20", " ");
//
//                } else if (url.getProtocol().equals("jar")) {
//
//                    String expected = "!/" + Recurso;
//                    String s = url.toString();
//                    s = s.substring(4);
//                    s = s.substring(0, s.length() - expected.length() + 1).replaceAll("%20", " ");
//                    File f = new File(new URL(s).toURI());
//                    PATH = f.getParentFile().getParentFile().getWorkingDirectoryPath().replaceAll("%20", " ");
//
//                }
//            } catch (Exception ex) {
//                PATH=".";
//            }
//        }
        if(PATH!=null)
            return PATH;
        if(WORKING_DIRECTORY== null)
            getWorkingDirectory();
        PATH = WORKING_DIRECTORY.getPath();
        return PATH;
    }

    public static File getWorkingDirectory() {

        String Recurso = WorkingDirectory.class.getSimpleName() + ".class";
        if (WORKING_DIRECTORY == null) {
            try {
                URL url = WorkingDirectory.class.getResource(Recurso);
                if (url.getProtocol().equals("file")) {
                    File f = new File(url.toURI());
                    do {

                        f = f.getParentFile();
                    } while (!f.isDirectory());

                    WORKING_DIRECTORY = f;
                } else if (url.getProtocol().equals("jar")) {
                    String expected = "!/" + Recurso;
                    String s = url.toString();
                    s = s.substring(4);
                    s = s.substring(0, s.length() - expected.length() + 1);
                    File f = new File(new URL(s).toURI());
                    do {

                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    WORKING_DIRECTORY = f;
                }
            } catch (Exception e) {
                WORKING_DIRECTORY = new File(".");
            }
        }
        return WORKING_DIRECTORY;
    }

}
