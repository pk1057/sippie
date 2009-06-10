/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.filefilter;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Christian
 */
public class XmlFileFilter extends FileFilter {

    public boolean accept(File f) {
        if(f.isDirectory()==true)
            return true;
        if(f.getName().endsWith(".xml"))
            return true;
        return false;
    }
    public String getDescription() {
        return "*.xml";
    }
}