/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res.interfaces;

import java.awt.Toolkit;

/**
 *
 * @author Christian
 */
public interface IConstants {
    String fsep = System.getProperty("file.separator");
    double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
}
