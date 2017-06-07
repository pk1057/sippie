/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Christian
 */
public class CloseCommand extends WindowAdapter implements ActionListener {

    private Window w;
    private JFrame parent;
    private boolean exit = false;
    private boolean cancel = false;

    public CloseCommand(Window w, JFrame parent) {
        this.w = w;
        this.parent = parent;
    }

    public CloseCommand(Window w, JFrame parent, boolean exit) {
        this.w = w;
        this.exit = exit;
        this.parent = parent;
    }

    public CloseCommand(Window w, JFrame parent, boolean exit, boolean cancel) {
        this.w = w;
        this.exit = exit;
        this.cancel = cancel;
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        if(cancel) ((SippXMLConverter)parent).setCanceled(true);
        if(exit) {      // 0 ... opUAC 1 ... opUAS
            int[] ret = ((SippXMLConverter)parent).askForClosing();

            if(!(ret[1]==JOptionPane.CANCEL_OPTION||ret[0]==JOptionPane.CANCEL_OPTION)) {
                File uac = new File("uac_temp.xml");
                File uas = new File("uas_temp.xml");
                uac.delete();
                uas.delete();
                w.dispose();
                System.exit(0);
            }
        }
        else
            w.dispose();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        if(cancel) ((SippXMLConverter)parent).setCanceled(true);
        if(exit) {          // 0 ... opUAC 1 ... opUAS
            int[] ret = ((SippXMLConverter)parent).askForClosing();
            
            if(!(ret[1]==JOptionPane.CANCEL_OPTION||ret[0]==JOptionPane.CANCEL_OPTION)) {
                File uac = new File("uac_temp.xml");
                File uas = new File("uas_temp.xml");
                uac.delete();
                uas.delete();
                w.dispose();
                System.exit(0);
            }
        }
        else
            w.dispose();
    }
}