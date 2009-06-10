/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.SearchReplace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Christian
 */
public class SearchReplaceCommand implements ActionListener {
    
    private SippXMLConverter parent;
    
    public SearchReplaceCommand(JFrame parent) {
        this.parent = (SippXMLConverter) parent;
    }


    public void actionPerformed(ActionEvent e) {
        new SearchReplace(parent).setVisible(true);
    }

}
