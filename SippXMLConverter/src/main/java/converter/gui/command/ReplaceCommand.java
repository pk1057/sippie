/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.Replace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Christian
 */
public class ReplaceCommand implements ActionListener {

    JFrame parent;

    public ReplaceCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        new Replace(parent).setVisible(true);
    }
}
