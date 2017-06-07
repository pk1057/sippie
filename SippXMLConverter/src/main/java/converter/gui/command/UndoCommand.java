/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.res.UndoRedoList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import org.gjt.sp.jedit.buffer.BufferListener;
import org.gjt.sp.jedit.buffer.JEditBuffer;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class UndoCommand implements BufferListener {

    JFrame parent;
    JTabbedPane tpText;
    TextArea taUAS;
    TextArea taUAC;
    JLabel statusBar;
    JButton bUndo;
    JButton bRedo;

    public UndoCommand(JFrame parent) {
        this.parent = parent;
        tpText = ((SippXMLConverter)parent).getTpText();
        taUAS = ((SippXMLConverter)parent).getTaUAS();
        taUAC = ((SippXMLConverter)parent).getTaUAC();
        statusBar = ((SippXMLConverter)parent).getStatusBar();
        bUndo = ((SippXMLConverter)parent).getBUndo();
        bRedo = ((SippXMLConverter)parent).getBRedo();
    }

    public void preContentInserted(JEditBuffer buffer, int startLine, int offset, int numLines, int length) {
            
            String s = buffer.getText(0, offset)+buffer.getText(offset+length,buffer.getLength()-offset-length);
            boolean pushUndoTask = ((SippXMLConverter)parent).isPushUndoTask();
            UndoRedoList<String> undoUAS = ((SippXMLConverter)parent).getUndoUAS();
            UndoRedoList<String> undoUAC = ((SippXMLConverter)parent).getUndoUAC();
            if(pushUndoTask) {
                if(tpText.getSelectedIndex()==0) {
                    System.out.println("s is should be in undo list now: "+s);
                    undoUAC.pushTask(s);
                    statusBar.setText(((SippXMLConverter)parent).getSaveUAC().isEmpty()?"unnamed *":((SippXMLConverter)parent).getSaveUAC()+" *");
                    ((SippXMLConverter)parent).setSavedUAC(false);
                }
                else {
                    undoUAS.pushTask(s);
                    statusBar.setText(((SippXMLConverter)parent).getSaveUAS().isEmpty()?"unnamed *":((SippXMLConverter)parent).getSaveUAS()+" *");
                    ((SippXMLConverter)parent).setSavedUAS(false);
                }
                bUndo.setEnabled(true);
                bRedo.setEnabled(false);
            }
        }
        public void preContentRemoved(JEditBuffer buffer, int startLine, int offset, int numLines, int length) {
            boolean pushUndoTask = ((SippXMLConverter)parent).isPushUndoTask();
            UndoRedoList<String> undoUAS = ((SippXMLConverter)parent).getUndoUAS();
            UndoRedoList<String> undoUAC = ((SippXMLConverter)parent).getUndoUAC();
            if(pushUndoTask) {
                if(tpText.getSelectedIndex()==0) {
                    undoUAC.pushTask(buffer.getText(0, buffer.getLength()));
                    statusBar.setText(((SippXMLConverter)parent).getSaveUAC().isEmpty()?"unnamed *":((SippXMLConverter)parent).getSaveUAC()+" *");
                    ((SippXMLConverter)parent).setSavedUAC(false);
                }
                else {
                    undoUAS.pushTask(buffer.getText(0,buffer.getLength()));
                    statusBar.setText(((SippXMLConverter)parent).getSaveUAS().isEmpty()?"unnamed *":((SippXMLConverter)parent).getSaveUAS()+" *");
                    ((SippXMLConverter)parent).setSavedUAS(false);
                }
                bUndo.setEnabled(true);
                bRedo.setEnabled(false);
            }
        }
        public void foldLevelChanged(JEditBuffer arg0, int arg1, int arg2) {}
        public void contentInserted(JEditBuffer arg0, int arg1, int arg2, int arg3, int arg4) {}
        public void contentRemoved(JEditBuffer arg0, int arg1, int arg2, int arg3, int arg4) {}
        public void transactionComplete(JEditBuffer arg0) {}
        public void foldHandlerChanged(JEditBuffer arg0) {}
        public void bufferLoaded(JEditBuffer arg0) {}
}
