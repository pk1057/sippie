/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Christian
 */
public class SaveCommand implements ActionListener {

    JFrame parent;

    public SaveCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        ((SippXMLConverter)parent).save(e.getActionCommand(),((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS());
    }
}
