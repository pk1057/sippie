/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.dialog;

import converter.gui.SippXMLConverter;
import converter.gui.command.CloseCommand;
import converter.res.interfaces.IConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class SearchReplace extends JDialog implements IConstants {

    private SippXMLConverter parent;

    private JLabel lSearch = new JLabel("Search for");
    private JLabel lReplace = new JLabel("Replace with");

    private JTextField tfSearch = new JTextField(10);
    private JTextField tfReplace = new JTextField(10);

    private JButton bCancel = new JButton("Cancel");
    private JButton bReplaceAll = new JButton("Replace all");

    public SearchReplace(JFrame parent) {
        setTitle("Search and Replace");
        this.parent = (SippXMLConverter) parent;
        if(parent==null) System.out.println("null");
        else System.out.println("not null");
        makeLayout();
        addListeners();
        setModal(true);
        Point location = parent.getLocation();
        int width = (parent.getWidth()-this.getWidth())/2;
        int height = (parent.getSize().height-this.getHeight())/2;
        setLocation(width<0?(int)(screenWidth-this.getWidth())/2:width+location.x,
                height<0?(int)(screenHeight-this.getHeight())/2:height+location.y);
    }

    private void makeLayout() {
        JPanel pNorth  = new JPanel(new GridLayout(2,2));
        pNorth.add(lSearch);
        pNorth.add(tfSearch);
        pNorth.add(lReplace);
        pNorth.add(tfReplace);

        JPanel pSouth = new JPanel(new FlowLayout());
        pSouth.add(bCancel);
        pSouth.add(bReplaceAll);

        add(pNorth,BorderLayout.NORTH);
        add(pSouth,BorderLayout.SOUTH);
        pack();
    }

    private void addListeners() {
        bCancel.addActionListener(new CloseCommand(this,parent));
        this.addWindowListener(new CloseCommand(this, parent));
        bReplaceAll.addActionListener(new DoJobListener());
    }

    class DoJobListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(parent==null) System.out.println("parent null");
            if(parent.getTpText()==null) System.out.println("other null");
            TextArea ta =  (parent.getTpText().getSelectedIndex()==0?parent.getTaUAC():parent.getTaUAS());

            Rectangle rect = ta.getVisibleRect();
            String text = ta.getText();
            text = text.replace(tfSearch.getText(), tfReplace.getText());
            ta.setText(text);
            ta.scrollRectToVisible(rect);
            dispose();
        }

    }

}
