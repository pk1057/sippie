/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.InsertTag;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class InsertCommand implements ActionListener {

    JFrame parent;

    public InsertCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        TextArea ta = tpText.getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS();

        int pos = ta.getCaretPosition();
        ((SippXMLConverter)parent).resetOptionGUIElements();
        new InsertTag(parent).setVisible(true);
        if(!((SippXMLConverter)parent).getCanceled()) {
            if(tpText.getSelectedIndex()==0) ((SippXMLConverter)parent).getUndoUAC().pushTask(ta.getText());
            else ((SippXMLConverter)parent).getUndoUAS().pushTask(ta.getText());
            ((SippXMLConverter)parent).getBUndo().setEnabled(true);
            ((SippXMLConverter)parent).setPushUndoTask(false);
            ta.setText(new String(
                        new StringBuffer(ta.getText(0,ta.getLineEndOffset(ta.getCaretLine()))).append(((SippXMLConverter)parent).getNewValue()).append(ta.getText(ta.getLineEndOffset(ta.getCaretLine()),ta.getBufferLength()-ta.getLineEndOffset(ta.getCaretLine())))
            ));
            ((SippXMLConverter)parent).setPushUndoTask(true);
            ta.setCaretPosition(pos);
            if(tpText.getSelectedIndex()==0)
                ((SippXMLConverter)parent).setSavedUAC(false);
            else
                ((SippXMLConverter)parent).setSavedUAS(false);
            if((tpText.getSelectedIndex()==0&&((SippXMLConverter)parent).getSaveUAC().isEmpty())||(tpText.getSelectedIndex()==1&&((SippXMLConverter)parent).getSaveUAS().isEmpty()))
                ((SippXMLConverter)parent).getStatusBar().setText("unnamed *");
            else
                ((SippXMLConverter)parent).getStatusBar().setText((tpText.getSelectedIndex()==0?((SippXMLConverter)parent).getSaveUAC():((SippXMLConverter)parent).getSaveUAS())+" *");
        }
        else
            ((SippXMLConverter)parent).setCanceled(false);
    }
}