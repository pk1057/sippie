/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.ReplaceOnTheFly;
import converter.res.UndoRedoList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class ReplaceOnTheFlyCommand implements ActionListener {

    JFrame parent;

    public ReplaceOnTheFlyCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        TextArea taUAS = ((SippXMLConverter)parent).getTaUAS();
        TextArea taUAC = ((SippXMLConverter)parent).getTaUAC();
        JButton bUndo = ((SippXMLConverter)parent).getBUndo();
        UndoRedoList<String> undoUAS = ((SippXMLConverter)parent).getUndoUAS();
        UndoRedoList<String> undoUAC = ((SippXMLConverter)parent).getUndoUAC();
        new ReplaceOnTheFly(parent).setVisible(true);
        if(!((SippXMLConverter)parent).getCanceled()) {
            StringBuffer temp = new StringBuffer();
            TextArea ta = tpText.getSelectedIndex()==0?taUAC:taUAS;
            int pos = ta.getCaretPosition();
            if(!((SippXMLConverter)parent).getApplyForAll()) {
                String before = ta.getText(0, ta.getLineStartOffset(ta.getCaretLine()));
                String after = ta.getText(ta.getLineEndOffset(ta.getCaretLine()),ta.getBufferLength()-ta.getLineEndOffset(ta.getCaretLine()));
                temp.append(before).append(((SippXMLConverter)parent).getNewValue()).append("\n").append(after);
                ((SippXMLConverter)parent).setNewValue(new String(temp));
            }
            if(ta==taUAC) undoUAC.pushTask(ta.getText());
            else  undoUAS.pushTask(ta.getText());
            bUndo.setEnabled(true);
            ((SippXMLConverter)parent).setPushUndoTask(false);
            ta.setText(((SippXMLConverter)parent).getNewValue());
            ((SippXMLConverter)parent).setPushUndoTask(true);
            ta.setCaretPosition(pos);
        }
        ((SippXMLConverter)parent).setCanceled(false);
        ((SippXMLConverter)parent).setApplyForAll(false);
    }
}