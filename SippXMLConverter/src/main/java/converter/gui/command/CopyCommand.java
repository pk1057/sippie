/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.res.TransferClipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Christian
 */
public class CopyCommand implements ActionListener {

    TransferClipboard tc = new TransferClipboard();
    JFrame parent;

    public CopyCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        tc.setClipboardContents((((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS()).getSelectedText());
    }

}
