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
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class PasteCommand implements ActionListener {
    TransferClipboard tc = new TransferClipboard();
    JFrame parent;

    public PasteCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        TextArea ta = (((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS());
        int caretPos = ta.getCaretPosition();
        if(ta.getSelectedText()==null || ta.getSelectedText().isEmpty())
            ta.getBuffer().insert(caretPos, tc.getClipboardContents());
        else {
            int start = ta.getSelection(0).getStart();
            int end = ta.getSelection(0).getEnd();
            ta.getText(start, end-start);

            ta.getBuffer().remove(start, end-start);
            ta.getBuffer().insert(start, tc.getClipboardContents());
            
        }

    }
}
