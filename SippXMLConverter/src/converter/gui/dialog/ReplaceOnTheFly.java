/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.dialog;

import converter.gui.SippXMLConverter;
import converter.gui.command.CloseCommand;
import converter.op.Operation;
import converter.res.SequentialMap;
import converter.res.interfaces.IConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class ReplaceOnTheFly extends JDialog implements IConstants{

    private JFrame parent;

    private JTabbedPane tp = new JTabbedPane();
    private JCheckBox cbIP = new JCheckBox("IP");
    private JRadioButton rbMediaIP = new JRadioButton("Media");
    private JRadioButton rbLocalIP = new JRadioButton("Local");
    private JRadioButton rbRemoteIP = new JRadioButton("Remote");
    private JCheckBox cbPort = new JCheckBox("Port");
    private JCheckBox cbTransport = new JCheckBox("Transport");
    private JRadioButton rbRemotePort = new JRadioButton("Remote");
    private JRadioButton rbLocalPort = new JRadioButton("Local");
    private JRadioButton rbMediaPort = new JRadioButton("Media");
    private JCheckBox cbTag = new JCheckBox("Tag");
    private JTextField tfTagName = new JTextField(10);
    private JTextField tfValue = new JTextField(10);

    private JButton bApply[] = {new JButton("Apply"),new JButton("Apply")};
    private JButton bApplyForAll = new JButton("Apply for all");
    private JButton bCancel[] = {new JButton("Cancel"),new JButton("Cancel")};

    private JCheckBox cbLastCallID = new JCheckBox("Last Call-ID");
    private JCheckBox cbLastCseq = new JCheckBox("Last Cseq");
    private JCheckBox cbLastTo = new JCheckBox("Last To");
    private JCheckBox cbLastFrom = new JCheckBox("Last From");
    private JCheckBox cbLastVia = new JCheckBox("Last Via");
    private SequentialMap<JCheckBox,JTextField> addLast = new SequentialMap<JCheckBox,JTextField>();

    private JPanel pIP = new JPanel(new GridLayout(2,1));
    private JPanel pPort = new JPanel(new GridLayout(2,1));
    private JPanel pTag = new JPanel(new GridLayout(2,1));
    private JPanel pFlowDown = new JPanel(new GridLayout(10,2));
    private JPanel pRes = new JPanel(new BorderLayout());

    public ReplaceOnTheFly(JFrame parent) {
        this.parent = parent;

        makeLayout();
        addListeners();
        setResizable(false);
        setModal(true);
        Point location = parent.getLocation();
        int width = (parent.getWidth()-this.getWidth())/2;
        int height = (parent.getSize().height-this.getHeight())/2;
        setLocation(width<0?(int)(screenWidth-this.getWidth())/2:width+location.x,
                height<0?(int)(screenHeight-this.getHeight())/2:height+location.y);
    }

    private void makeLayout() {
        JPanel pIPFlow1 = new JPanel(new FlowLayout());
        JPanel pPortFlow1 = new JPanel(new FlowLayout());
        JPanel pTagFlow1 = new JPanel(new FlowLayout());
        JPanel pIPFlow2 = new JPanel(new FlowLayout());
        JPanel pPortFlow2 = new JPanel(new FlowLayout());
        JPanel pTagFlow2 = new JPanel(new FlowLayout());

        ButtonGroup bg1 = new ButtonGroup();
        ButtonGroup bg2 = new ButtonGroup();

        rbLocalIP.setEnabled(false);rbRemoteIP.setEnabled(false);rbMediaIP.setEnabled(false);
        rbLocalPort.setEnabled(false);rbRemotePort.setEnabled(false);rbMediaPort.setEnabled(false);
        tfValue.setEnabled(false);tfTagName.setEnabled(false);
        bg1.add(rbLocalIP);
        bg1.add(rbMediaIP);
        bg1.add(rbRemoteIP);
        bg2.add(rbLocalPort);
        bg2.add(rbMediaPort);
        bg2.add(rbRemotePort);

        pIPFlow1.add(cbIP);
        pIPFlow2.add(rbMediaIP);
        pIPFlow2.add(rbLocalIP);
        pIPFlow2.add(rbRemoteIP);
        pIP.add(pIPFlow1);
        pIP.add(pIPFlow2);
        pIP.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pPortFlow1.add(cbPort);
        pPortFlow2.add(rbMediaPort);
        pPortFlow2.add(rbLocalPort);
        pPortFlow2.add(rbRemotePort);
        pPort.add(pPortFlow1);
        pPort.add(pPortFlow2);
        pPort.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel pFlowTrans = new JPanel(new FlowLayout());

        pFlowTrans.add(cbTransport);
        pFlowTrans.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pTagFlow1.add(cbTag);
        pTagFlow2.add(new JLabel("Tag name"));
        pTagFlow2.add(tfTagName);
        pTagFlow2.add(new JLabel("Replacing string"));
        pTagFlow2.add(tfValue);
        pTag.add(pTagFlow1);
        pTag.add(pTagFlow2);
        pTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pButtons.add(bApply[0]);
        pButtons.add(bApplyForAll);
        pButtons.add(bCancel[0]);

        JPanel pAll = new JPanel(new GridLayout(5,1));
        pAll.add(pIP);
        pAll.add(pPort);
        pAll.add(pFlowTrans);
        pAll.add(pTag);
        pAll.add(pButtons);

        tp.addTab("Line replacing", pAll);

        JPanel pFlowUp = new JPanel(new FlowLayout());

        pFlowUp.add(cbLastVia);
        pFlowUp.add(cbLastTo);
        pFlowUp.add(cbLastFrom);
        pFlowUp.add(cbLastCseq);
        pFlowUp.add(cbLastCallID);

        for(int i=0;i<10;i++) {
            addLast.put(new JCheckBox("Add Last"), new JTextField(10));
            if(i!=0) {
                addLast.lastKey().setVisible(false);
                addLast.get(addLast.lastKey()).setVisible(false);
            }
            pFlowDown.add(addLast.getKey(i));
            pFlowDown.add(addLast.get(addLast.getKey(i)));
        }

        pButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pButtons.add(bApply[1]);
        pButtons.add(bCancel[1]);

        pRes.add(pFlowUp,BorderLayout.NORTH);
        pRes.add(pFlowDown, BorderLayout.CENTER);
        pRes.add(pButtons,BorderLayout.SOUTH);
        tp.addTab("Last replacing", pRes);
        add(tp);
        pack();
    }

    private int determineType(int what) {
        int type = 0;

        switch(what) {
            case 0: type = (rbRemoteIP.isSelected()?1:type);
                    type = (rbMediaIP.isSelected()?2:type);
                    break;
            case 1: type = (rbRemotePort.isSelected()?1:type);
                    type = (rbMediaPort.isSelected()?2:type);
                    break;
        }
        return type;
    }

    private String replaceLine(String line) {
        if(cbIP.isSelected()) {
            switch(determineType(0)) {
                case 0: line = Operation.replaceIP(line,"local");
                    break;
                case 1: line = Operation.replaceIP(line,"remote");
                    break;
                case 2: line = Operation.replaceIP(line,"media");
                    break;
            }
        }
        if(cbPort.isSelected()) {
            switch(determineType(1)) {
                case 0: line = Operation.replacePort(line,"local");
                    break;
                case 1: line = Operation.replacePort(line,"remote");
                    break;
                case 2: line = Operation.replacePort(line,"media");
                    break;
            }
        }
        if(cbTransport.isSelected()) {
            line = line.replace("UDP", "[transport]");
            line = line.replace("TCP", "[transport]");
        }
        if(cbTag.isSelected()) line = Operation.replaceTag(line, tfTagName.getText(), tfValue.getText());
        return line;
    }

    private void addListeners() {
        addWindowListener(new CloseCommand(this,parent,false,true));
        bApply[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea ta = ((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS();
                String temp = ta.getLineText(ta.getCaretLine());
                ((SippXMLConverter)parent).setNewValue(replaceLine(temp));
                ((SippXMLConverter)parent).setApplyForAll(false);
                dispose();
            }
        });
        bApplyForAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea ta = ((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS();
                StringTokenizer st = new StringTokenizer(ta.getBuffer().getLineText(ta.getCaretLine()),":=",true);

                String key = st.nextToken();
                String value="";
                StringBuffer temp = new StringBuffer();
                while(st.hasMoreTokens())
                    value += st.nextToken();
                value = value.substring(1).trim();
                for(int i=0;i<ta.getLineCount();i++) {
                    String line = ta.getLineText(i);
                    if(line.contains(key)) temp.append(replaceLine(line)).append("\n");
                    else temp.append(line).append("\n");
                }
                ((SippXMLConverter)parent).setNewValue(new String(temp));
                ((SippXMLConverter)parent).setApplyForAll(true);
                dispose();
            }
        });
        for(int i=0;i<2;i++)
            bCancel[i].addActionListener(new CloseCommand(this,parent,false,true));
        bApply[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea ta = ((SippXMLConverter)parent).getTpText().getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS();
                StringBuffer temp = new StringBuffer();
                StringTokenizer st = new StringTokenizer(ta.getBuffer().getLineText(ta.getCaretLine()),":=",true);
                String key = st.nextToken();
                Vector<String> lasts = new Vector<String>();
                if(cbLastCallID.isSelected()) lasts.add("Call-ID");
                if(cbLastFrom.isSelected()) lasts.add("From");
                if(cbLastTo.isSelected()) lasts.add("To");
                if(cbLastVia.isSelected()) lasts.add("Via");
                if(cbLastCseq.isSelected()) lasts.add("Cseq");
                for(JCheckBox cb : addLast.keyList())
                    if(cb.isSelected()) lasts.add(addLast.get(cb).getText());
                for(int i=0;i<ta.getLineCount();i++) {
                    String line = ta.getLineText(i);
                    boolean appended=false;
                    for(String last : lasts)
                        if(!line.trim().isEmpty()&&new StringTokenizer(line,":=").nextToken().equalsIgnoreCase(last)) {
                            temp.append("[last_"+last+":]").append("\n");
                            appended=true;
                        }
                    if(!appended) temp.append(line).append("\n");
                    else appended = false;
                    JTabbedPane tp = new JTabbedPane();

                }
                ((SippXMLConverter)parent).setNewValue(new String(temp));
                ((SippXMLConverter)parent).setApplyForAll(true);
                dispose();
            }
        });
        for(JCheckBox cb : addLast.keyList())
            cb.addItemListener(new AddListener());
        cbIP.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    rbLocalIP.setEnabled(true);
                    rbMediaIP.setEnabled(true);
                    rbRemoteIP.setEnabled(true);
                }
                else {
                    rbLocalIP.setEnabled(false);
                    rbMediaIP.setEnabled(false);
                    rbRemoteIP.setEnabled(false);
                }
            }
        });
        cbPort.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    rbLocalPort.setEnabled(true);
                    rbMediaPort.setEnabled(true);
                    rbRemotePort.setEnabled(true);
                }
                else {
                    rbLocalPort.setEnabled(false);
                    rbMediaPort.setEnabled(false);
                    rbRemotePort.setEnabled(false);
                }
            }
        });
        cbTag.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED) {
                    tfValue.setEnabled(true);
                    tfTagName.setEnabled(true);
                }
                else {
                    tfTagName.setEnabled(false);
                    tfValue.setEnabled(false);
                }
            }
        });
    }

    class AddListener implements ItemListener {

        public void itemStateChanged(ItemEvent arg0) {
            if(arg0.getStateChange()==ItemEvent.SELECTED) {
                JCheckBox key = (JCheckBox)arg0.getItem();
                if(addLast.getIndex(key)==9) return;
                JCheckBox visibleKey = addLast.getKey(addLast.getIndex(key)+1);
                JTextField visibleValue = addLast.get(visibleKey);
                visibleKey.setVisible(true);
                visibleValue.setVisible(true);
            }
        }

    }
}