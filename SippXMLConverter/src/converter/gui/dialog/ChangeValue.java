/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.dialog;

import converter.gui.SippXMLConverter;
import converter.res.interfaces.IConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Christian
 */
public class ChangeValue extends JDialog implements IConstants {

    private JFrame parent;

    private JLabel lKey;
    private JTextField tfValue;
    private JButton bApply = new JButton("Apply");
    private JButton bApplyForAll = new JButton("Apply for all");
    private JButton bCancel = new JButton("Cancel");

    public ChangeValue(String key, String value, JFrame parent) {
        this.parent = parent;

        lKey = new JLabel("Insert new value for "+key);
        tfValue = new JTextField(value);
        makeLayout();
        addListeners();
        setModal(true);
        setResizable(false);
        int width = (parent.getSize().width-this.getWidth())/2;
        int height = (parent.getSize().height-this.getHeight())/2;
        setLocation(width<0?(int)(screenWidth-this.getWidth())/2:width+parent.getLocation().x,
                height<0?(int)(screenHeight-this.getHeight())/2:height+parent.getLocation().y);
    }

    public void makeLayout() {
        JPanel pWest = new JPanel(new FlowLayout());
        JPanel pCenter = new JPanel(new FlowLayout());
        JPanel pSouth = new JPanel (new FlowLayout());

        pWest.add(lKey);
        pCenter.add(tfValue);
        pSouth.add(bApply);
        pSouth.add(bApplyForAll);
        pSouth.add(bCancel);

        add(pWest,BorderLayout.WEST);
        add(pCenter,BorderLayout.CENTER);
        add(pSouth,BorderLayout.SOUTH);

        pack();
    }

    public void addListeners()  {
        bApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SippXMLConverter)parent).setApplyForAll(false);
                ((SippXMLConverter)parent).setCanceled(false);
                System.out.println("*"+tfValue.getText()+"\n*");
                ((SippXMLConverter)parent).setNewValue(tfValue.getText()+"\n");
                dispose();
            }
        });

        bApplyForAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SippXMLConverter)parent).setApplyForAll(true);
                ((SippXMLConverter)parent).setCanceled(false);
                ((SippXMLConverter)parent).setNewValue(tfValue.getText()+"\n");
                dispose();
            }
        });

        bCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((SippXMLConverter)parent).setCanceled(true);
                dispose();
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ((SippXMLConverter)parent).setCanceled(true);
                dispose();
            }
        });
    }
}