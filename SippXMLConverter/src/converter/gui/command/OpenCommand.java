/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.filefilter.XmlFileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class OpenCommand implements ActionListener {

    JFrame parent;

    public OpenCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        TextArea taUAS = ((SippXMLConverter)parent).getTaUAS();
        TextArea taUAC = ((SippXMLConverter)parent).getTaUAC();
        JLabel statusBar = ((SippXMLConverter)parent).getStatusBar();
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(((SippXMLConverter)parent).getWorkingDir());
        fc.setAcceptAllFileFilterUsed(true);
        fc.setFileFilter(new XmlFileFilter());
        fc.showOpenDialog(parent);
        File openFile = fc.getSelectedFile();
        if(openFile!=null) {
            try {
                ((SippXMLConverter)parent).setWorkingDir(fc.getCurrentDirectory());
                TextArea ta = TextArea.createTextArea();
                String erg = ((SippXMLConverter)parent).readList(openFile.getAbsolutePath(),ta);
                int option = 0;
                if(erg.contains("<recv response")) {
                    tpText.setSelectedIndex(0);
                    if(!((SippXMLConverter)parent).isSavedUAC())
                        if((option=JOptionPane.showConfirmDialog(parent, "Changes were made in UAC xml file, which haven't been saved. Do you want to save it now?","UAC not saved",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.CANCEL_OPTION)
                            return;
                        else if(option==JOptionPane.YES_OPTION)
                            ((SippXMLConverter)parent).save("",taUAC);
                    ((SippXMLConverter)parent).setSaveUAC(openFile.getCanonicalPath());
                    ((SippXMLConverter)parent).readList(((SippXMLConverter)parent).getSaveUAC(),taUAC);
                }
                else {
                     tpText.setSelectedIndex(1);
                    if(!((SippXMLConverter)parent).isSavedUAS())
                       if((option=JOptionPane.showConfirmDialog(parent, "Changes were made in UAS xml file, which haven't been saved. Do you want to save it now?","UAS not saved",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.CANCEL_OPTION)
                           return;
                       else if(option==JOptionPane.YES_OPTION)
                           ((SippXMLConverter)parent).save("",taUAS);
                    ((SippXMLConverter)parent).setSaveUAS(openFile.getCanonicalPath());
                    ((SippXMLConverter)parent).readList(((SippXMLConverter)parent).getSaveUAS(),taUAS);
                }
                statusBar.setText(openFile.getCanonicalPath());
            }
            catch(FileNotFoundException fnfe) {
                JOptionPane.showMessageDialog(parent, "The file name you entered hadn't been found on the filesystem","File not found",JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException ioe) {
                JOptionPane.showMessageDialog(parent, "Problems occured while reading the file","I/O Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
