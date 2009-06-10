/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.CallIDSelection;
import converter.gui.filefilter.PcapFileFilter;
import converter.op.Operation;
import converter.res.UndoRedoList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class ConvertionCommand implements ActionListener {

    private boolean reread = false;
    private JFrame parent;

    public ConvertionCommand(boolean reread, JFrame parent) {
        this.reread = reread;
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        UndoRedoList<String> undoUAS = ((SippXMLConverter)parent).getUndoUAS();
        UndoRedoList<String> undoUAC = ((SippXMLConverter)parent).getUndoUAC();
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        JLabel statusBar = ((SippXMLConverter)parent).getStatusBar();
        TextArea taUAS = ((SippXMLConverter)parent).getTaUAS();
        TextArea taUAC = ((SippXMLConverter)parent).getTaUAC();
        JButton bUndo = ((SippXMLConverter)parent).getBUndo();
        JButton bRedo = ((SippXMLConverter)parent).getBRedo();
        if(!reread) {
            int uacOption = -1;
            int uasOption = -1;
            if(!((SippXMLConverter)parent).isSavedUAC())
                uacOption = JOptionPane.showConfirmDialog(parent, "UAC not saved. Do you want to save it now?", "Save UAC", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(uacOption==JOptionPane.YES_OPTION)
                ((SippXMLConverter)parent).save("",taUAC);
            if(!((SippXMLConverter)parent).isSavedUAS())
                uasOption = JOptionPane.showConfirmDialog(parent, "UAS not saved. Do you want to save it now?", "Save UAS", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            tpText.setSelectedIndex(1);
            if(uasOption==JOptionPane.YES_OPTION)
                ((SippXMLConverter)parent).save("",taUAS);
            if(uacOption==JOptionPane.CANCEL_OPTION||uasOption==JOptionPane.CANCEL_OPTION)
                return;
            JOptionPane.showMessageDialog(parent, "Please select the pcap-file to convert","Pcap-file selection",JOptionPane.INFORMATION_MESSAGE);
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(((SippXMLConverter)parent).getWorkingDir());
            fc.setAcceptAllFileFilterUsed(true);
            fc.setFileFilter(new PcapFileFilter());
            fc.showOpenDialog(parent);
            File f = fc.getSelectedFile();
            if(f!=null) {
                ((SippXMLConverter)parent).setWorkingDir(fc.getCurrentDirectory());
                if(!f.canRead()) {
                    JOptionPane.showMessageDialog(parent, "You don't have the permission to read this file","File not readable",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                ((SippXMLConverter)parent).setPcapFile(f.getAbsolutePath());
                new CallIDSelection(parent).setVisible(true);
                undoUAC = new UndoRedoList<String>();
                undoUAS = new UndoRedoList<String>();
                bUndo.setEnabled(false);
                bRedo.setEnabled(false);
            }
            else {
                ((SippXMLConverter)parent).setCanceled(true);
            }
        }
        else {
            TextArea ta = tpText.getSelectedIndex()==0?taUAC:taUAS;
            if(JOptionPane.showConfirmDialog(parent, "You are going to re-read the pcap-file. All changes made are lost. Do you really want to re-read the file?","Re-read of pacap-file",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE)==JOptionPane.NO_OPTION)
                return;
            undoUAC.pushTask(taUAC.getText());
            undoUAS.pushTask(taUAS.getText());
            bUndo.setEnabled(true);
        }
        if(!((SippXMLConverter)parent).getPcapFile().isEmpty()&&!((SippXMLConverter)parent).getCanceled()) {

            try {
                Operation.convert(((SippXMLConverter)parent).getScenarioName(), ((SippXMLConverter)parent).getPcapFile(), ((SippXMLConverter)parent).getSelectedCallID());
                ((SippXMLConverter)parent).getBReread().setEnabled(true);
                ((SippXMLConverter)parent).readList("temp_uac.xml",taUAC);
                ((SippXMLConverter)parent).readList("temp_uas.xml",taUAS);
                ((SippXMLConverter)parent).setSaveUAC("");
                ((SippXMLConverter)parent).setSaveUAS("");
                ((SippXMLConverter)parent).setSavedUAC(false);
                ((SippXMLConverter)parent).setSavedUAS(false);
                if(!reread) statusBar.setText("unnamed *");
                else {
                    if(tpText.getSelectedIndex()==0)
                        if(((SippXMLConverter)parent).getSaveUAC().isEmpty()) statusBar.setText("unnamed *");
                        else statusBar.setText(((SippXMLConverter)parent).getSaveUAC()+" *");
                    else
                        if(((SippXMLConverter)parent).getSaveUAS().isEmpty()) statusBar.setText("unnamed *");
                        else statusBar.setText(((SippXMLConverter)parent).getSaveUAS()+" *");
                }
            }
            catch(IOException ioe) {
                JOptionPane.showMessageDialog(parent, "Some problems occured while generating and writing the xml-file", "I/O-Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        ((SippXMLConverter)parent).setCanceled(false);
    }
}
