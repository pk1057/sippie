/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.dialog;

import converter.res.interfaces.ICheckbox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import converter.res.interfaces.IConstants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Christian
 */
public class Replace extends JDialog implements IConstants, ICheckbox {

    private JFrame parent;

    private JButton bOK = new JButton("OK");
    private JButton bCancel = new JButton("Cancel");

    private JRadioButton uas = new JRadioButton("UAS");
    private JRadioButton uac = new JRadioButton("UAC");
    private ButtonGroup bg = new ButtonGroup();
    private JPanel p[] = {new JPanel(new GridLayout(4,2)),new JPanel(new GridLayout(4,2))};

    private boolean uasState[] = new boolean[29];
    private boolean uacState[] = new boolean[29];

    public Replace(JFrame parent) {
        this.parent = parent;
        makeLayout();
        addListeners();
        setModal(true);
        setTitle("Standard replacing rules");
        setResizable(false);
        int width = (parent.getSize().width-this.getWidth())/2;
        int height = (parent.getSize().height-this.getHeight())/2;
        setLocation(width<0?(int)(screenWidth-this.getWidth())/2:width+parent.getLocation().x,
                height<0?(int)(screenHeight-this.getHeight())/2:height+parent.getLocation().y);
    }

    private void makeLayout() {

        JPanel pMethod[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pVia1[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pVia2[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pViaGrid[] = {new JPanel(new GridLayout(2,3)),new JPanel(new GridLayout(2,3))};
        JPanel pFrom1[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pFrom2[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pFromGrid[] = {new JPanel(new GridLayout(2,3)),new JPanel(new GridLayout(2,3))};
        JPanel pTo1[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pTo2[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pToGrid[] = {new JPanel(new GridLayout(2,3)),new JPanel(new GridLayout(2,3))};
        JPanel pCallID[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pCseq[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pContact[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pSDP1[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pSDP2[] = {new JPanel(new FlowLayout()),new JPanel(new FlowLayout())};
        JPanel pSDPGrid[] = {new JPanel(new GridLayout(2,3)),new JPanel(new GridLayout(2,3))};
        JPanel pOK = new JPanel(new FlowLayout());

        pMethod[0].add(cbServiceMethod[0]);pMethod[0].add(cbIPMethod[0]);pMethod[0].add(cbPortMethod[0]);
        pVia1[0].add(cbTransportVia[0]);pVia1[0].add(cbIPVia[0]);pVia1[0].add(cbPortVia[0]);pVia2[0].add(cbBranch[0]);pVia2[0].add(cbViaL[0]);
        pViaGrid[0].add(pVia1[0]);pViaGrid[0].add(pVia2[0]);
        pFrom1[0].add(cbIPFrom[0]);pFrom1[0].add(cbPortFrom[0]);pFrom1[0].add(cbCallNumberFrom[0]);pFrom2[0].add(cbFromL[0]);
        pFromGrid[0].add(pFrom1[0]);pFromGrid[0].add(pFrom2[0]);
        pTo1[0].add(cbServiceTo[0]);pTo1[0].add(cbIPTo[0]);pTo1[0].add(cbPortTo[0]);pTo2[0].add(cbPeerTagParam[0]);pTo2[0].add(cbCallNumberTo[0]);pTo2[0].add(cbToL[0]);
        pToGrid[0].add(pTo1[0]);pToGrid[0].add(pTo2[0]);
        pCallID[0].add(cbCallIDL[0]);
        pCseq[0].add(cbCseq[0]);pCseq[0].add(cbCseqL[0]);
        pContact[0].add(cbIPContact[0]);pContact[0].add(cbPortContact[0]);pContact[0].add(cbTransportContact[0]);
        pSDP1[0].add(cbIPTypeOrigin[0]);pSDP1[0].add(cbIPOrigin[0]);pSDP1[0].add(cbIPTypeConnData[0]);pSDP2[0].add(cbIPConnData[0]);pSDP2[0].add(cbPortMediDescription[0]);
        pSDPGrid[0].add(pSDP1[0]);pSDPGrid[0].add(pSDP2[0]);

        pMethod[0].setBorder(BorderFactory.createTitledBorder("Method"));
        pViaGrid[0].setBorder(BorderFactory.createTitledBorder("Via"));
        pFromGrid[0].setBorder(BorderFactory.createTitledBorder("From"));
        pToGrid[0].setBorder(BorderFactory.createTitledBorder("To"));
        pCallID[0].setBorder(BorderFactory.createTitledBorder("Call-ID"));
        pCseq[0].setBorder(BorderFactory.createTitledBorder("Cseq"));
        pContact[0].setBorder(BorderFactory.createTitledBorder("Contact"));
        pSDPGrid[0].setBorder(BorderFactory.createTitledBorder("Session Description Protocol"));


        p[0].add(pMethod[0]);p[0].add(pViaGrid[0]);
        p[0].add(pContact[0]);p[0].add(pToGrid[0]);
        p[0].add(pCallID[0]);p[0].add(pFromGrid[0]);
        p[0].add(pCseq[0]);p[0].add(pSDPGrid[0]);

        pMethod[1].add(cbServiceMethod[1]);pMethod[1].add(cbIPMethod[1]);pMethod[1].add(cbPortMethod[1]);
        pVia1[1].add(cbTransportVia[1]);pVia1[1].add(cbIPVia[1]);pVia1[1].add(cbPortVia[1]);pVia2[1].add(cbBranch[1]);pVia2[1].add(cbViaL[1]);
        pViaGrid[1].add(pVia1[1]);pViaGrid[1].add(pVia2[1]);
        pFrom1[1].add(cbIPFrom[1]);pFrom1[1].add(cbPortFrom[1]);pFrom1[1].add(cbCallNumberFrom[1]);pFrom2[1].add(cbFromL[1]);
        pFromGrid[1].add(pFrom1[1]);pFromGrid[1].add(pFrom2[1]);
        pTo1[1].add(cbServiceTo[1]);pTo1[1].add(cbIPTo[1]);pTo1[1].add(cbPortTo[1]);pTo2[1].add(cbPeerTagParam[1]);pTo2[1].add(cbCallNumberTo[1]);pTo2[1].add(cbToL[1]);
        pToGrid[1].add(pTo1[1]);pToGrid[1].add(pTo2[1]);
        pCallID[1].add(cbCallIDL[1]);
        pCseq[1].add(cbCseq[1]);pCseq[1].add(cbCseqL[1]);
        pContact[1].add(cbIPContact[1]);pContact[1].add(cbPortContact[1]);pContact[1].add(cbTransportContact[1]);
        pSDP1[1].add(cbIPTypeOrigin[1]);pSDP1[1].add(cbIPOrigin[1]);pSDP1[1].add(cbIPTypeConnData[1]);pSDP2[1].add(cbIPConnData[1]);pSDP2[1].add(cbPortMediDescription[1]);
        pSDPGrid[1].add(pSDP1[1]);pSDPGrid[1].add(pSDP2[1]);

        pMethod[1].setBorder(BorderFactory.createTitledBorder("Method"));
        pViaGrid[1].setBorder(BorderFactory.createTitledBorder("Via"));
        pFromGrid[1].setBorder(BorderFactory.createTitledBorder("From"));
        pToGrid[1].setBorder(BorderFactory.createTitledBorder("To"));
        pCallID[1].setBorder(BorderFactory.createTitledBorder("Call-ID"));
        pCseq[1].setBorder(BorderFactory.createTitledBorder("Cseq"));
        pContact[1].setBorder(BorderFactory.createTitledBorder("Contact"));
        pSDPGrid[1].setBorder(BorderFactory.createTitledBorder("Session Description Protocol"));

        p[1].add(pMethod[1]);p[1].add(pViaGrid[1]);
        p[1].add(pContact[1]);p[1].add(pToGrid[1]);
        p[1].add(pCallID[1]);p[1].add(pFromGrid[1]);
        p[1].add(pCseq[1]);p[1].add(pSDPGrid[1]);

        pOK.add(bOK);
        pOK.add(bCancel);

        bg.add(uac);
        bg.add(uas);
        JPanel pNorth = new JPanel(new FlowLayout());
        pNorth.add(uac);
        pNorth.add(uas);
        uac.setSelected(true);

        add(pNorth,BorderLayout.NORTH);
        add(uac.isSelected()?p[0]:p[1],BorderLayout.CENTER);

        add(pOK,BorderLayout.SOUTH);

        this.pack();
    }

    private void saveState() {
        uacState[0]=cbIPConnData[0].isSelected();uacState[1]=cbIPContact[0].isSelected();uacState[2]=cbIPFrom[0].isSelected();
        uacState[3]=cbIPMethod[0].isSelected();uacState[4]=cbIPOrigin[0].isSelected();uacState[5]=cbIPTo[0].isSelected();uacState[6]=cbIPTypeConnData[0].isSelected();
        uacState[7]=cbIPTypeOrigin[0].isSelected();uacState[8]=cbIPVia[0].isSelected();uacState[9]=cbCseq[0].isSelected();uacState[10]=cbCallNumberFrom[0].isSelected();
        uacState[11]=cbCallNumberTo[0].isSelected();uacState[12]=cbTransportContact[0].isSelected();uacState[13]=cbTransportVia[0].isSelected();uacState[14]=cbPortContact[0].isSelected();
        uacState[15]=cbPortFrom[0].isSelected();uacState[16]=cbPortMediDescription[0].isSelected();uacState[17]=cbPortMethod[0].isSelected();uacState[18]=cbPortTo[0].isSelected();
        uacState[19]=cbPortVia[0].isSelected();uacState[20]=cbViaL[0].isSelected();uacState[21]=cbToL[0].isSelected();uacState[22]=cbFromL[0].isSelected();uacState[23]=cbCseqL[0].isSelected();
        uacState[24]=cbCallIDL[0].isSelected();uacState[25]=cbBranch[0].isSelected();uacState[26]=cbPeerTagParam[0].isSelected();uacState[27]=cbServiceMethod[0].isSelected();uacState[28]=cbServiceTo[0].isSelected();

        uasState[0]=cbIPConnData[1].isSelected();uasState[1]=cbIPContact[1].isSelected();uasState[2]=cbIPFrom[1].isSelected();
        uasState[3]=cbIPMethod[1].isSelected();uasState[4]=cbIPOrigin[1].isSelected();uasState[5]=cbIPTo[1].isSelected();uasState[6]=cbIPTypeConnData[1].isSelected();
        uasState[7]=cbIPTypeOrigin[1].isSelected();uasState[8]=cbIPVia[1].isSelected();uasState[9]=cbCseq[1].isSelected();uasState[10]=cbCallNumberFrom[1].isSelected();
        uasState[11]=cbCallNumberTo[1].isSelected();uasState[12]=cbTransportContact[1].isSelected();uasState[13]=cbTransportVia[1].isSelected();uasState[14]=cbPortContact[1].isSelected();
        uasState[15]=cbPortFrom[1].isSelected();uasState[16]=cbPortMediDescription[1].isSelected();uasState[17]=cbPortMethod[1].isSelected();uasState[18]=cbPortTo[1].isSelected();
        uasState[19]=cbPortVia[1].isSelected();uasState[20]=cbViaL[1].isSelected();uasState[21]=cbToL[1].isSelected();uasState[22]=cbFromL[1].isSelected();uasState[23]=cbCseqL[1].isSelected();
        uasState[24]=cbCallIDL[1].isSelected();uasState[25]=cbBranch[1].isSelected();uasState[26]=cbPeerTagParam[1].isSelected();uasState[27]=cbServiceMethod[1].isSelected();uasState[28]=cbServiceTo[1].isSelected();
    }

    public void restoreState() {
        cbIPConnData[0].setSelected(uacState[0]);cbIPContact[0].setSelected(uacState[1]);cbIPFrom[0].setSelected(uacState[2]);
        cbIPMethod[0].setSelected(uacState[3]);cbIPOrigin[0].setSelected(uacState[4]);cbIPTo[0].setSelected(uacState[5]);cbIPTypeConnData[0].setSelected(uacState[6]);
        cbIPTypeOrigin[0].setSelected(uacState[7]);cbIPVia[0].setSelected(uacState[8]);cbCseq[0].setSelected(uacState[9]);cbCallNumberFrom[0].setSelected(uacState[10]);
        cbCallNumberTo[0].setSelected(uacState[11]);cbTransportContact[0].setSelected(uacState[12]);cbTransportVia[0].setSelected(uacState[13]);cbPortContact[0].setSelected(uacState[14]);
        cbPortFrom[0].setSelected(uacState[15]);cbPortMediDescription[0].setSelected(uacState[16]);cbPortMethod[0].setSelected(uacState[17]);cbPortTo[0].setSelected(uacState[18]);
        cbPortVia[0].setSelected(uacState[19]);cbViaL[0].setSelected(uacState[20]);cbToL[0].setSelected(uacState[21]);cbFromL[0].setSelected(uacState[22]);cbCseqL[0].setSelected(uacState[23]);
        cbCallIDL[0].setSelected(uacState[24]);cbBranch[0].setSelected(uacState[25]);cbPeerTagParam[0].setSelected(uacState[26]);cbServiceMethod[0].setSelected(uacState[27]);cbServiceTo[0].setSelected(uacState[28]);

        cbIPConnData[1].setSelected(uasState[0]);cbIPContact[1].setSelected(uasState[1]);cbIPFrom[1].setSelected(uasState[2]);
        cbIPMethod[1].setSelected(uasState[3]);cbIPOrigin[1].setSelected(uasState[4]);cbIPTo[1].setSelected(uasState[5]);cbIPTypeConnData[1].setSelected(uasState[6]);
        cbIPTypeOrigin[1].setSelected(uasState[7]);cbIPVia[1].setSelected(uasState[8]);cbCseq[1].setSelected(uasState[9]);cbCallNumberFrom[1].setSelected(uasState[10]);
        cbCallNumberTo[1].setSelected(uasState[11]);cbTransportContact[1].setSelected(uasState[12]);cbTransportVia[1].setSelected(uasState[13]);cbPortContact[1].setSelected(uasState[14]);
        cbPortFrom[1].setSelected(uasState[15]);cbPortMediDescription[1].setSelected(uasState[16]);cbPortMethod[1].setSelected(uasState[17]);cbPortTo[1].setSelected(uasState[18]);
        cbPortVia[1].setSelected(uasState[19]);cbViaL[1].setSelected(uasState[20]);cbToL[1].setSelected(uasState[21]);cbFromL[1].setSelected(uasState[22]);cbCseqL[1].setSelected(uasState[23]);
        cbCallIDL[1].setSelected(uasState[24]);cbBranch[1].setSelected(uasState[25]);cbPeerTagParam[1].setSelected(uasState[26]);cbServiceMethod[1].setSelected(uasState[27]);cbServiceTo[1].setSelected(uasState[28]);
    }

    public void addListeners() {
        bOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties p = new Properties();
                try {
                    p.load(new FileInputStream("main.properties"));
                } catch(IOException e1) {
                }
                String s[] = {"cb.service.method","cb.service.to","cb.ip.method","cb.port.method","cb.ip.via","cb.port.via",
                "cb.ip.from","cb.port.from","cb.ip.to","cb.port.to","cb.ip.contact","cb.port.contact","cb.transport.via",
                "cb.transport.contact","cb.callnumber.from","cb.callnumber.to","cb.peertagparam","cb.cseq","cb.branch",
                "cb.iptype.origin","cb.ip.origin","cb.iptype.conndata","cb.ip.conndata","cb.port.mediadescription",
                "cb.via.l","cb.from.l","cb.to.l","cb.cseq.l","cb.callid.l"
                };
                p.setProperty(s[0]+".uac",Boolean.toString(cbServiceMethod[0].isSelected()));p.setProperty(s[0]+".uas",Boolean.toString(cbServiceMethod[0].isSelected()));
                p.setProperty(s[1]+".uac",Boolean.toString(cbServiceTo[0].isSelected()));p.setProperty(s[1]+".uas",Boolean.toString(cbServiceTo[1].isSelected()));
                p.setProperty(s[2]+".uac",Boolean.toString(cbIPMethod[0].isSelected()));p.setProperty(s[2]+".uas",Boolean.toString(cbIPMethod[1].isSelected()));
                p.setProperty(s[3]+".uac",Boolean.toString(cbPortMethod[0].isSelected()));p.setProperty(s[3]+".uas",Boolean.toString(cbPortMethod[1].isSelected()));
                p.setProperty(s[4]+".uac",Boolean.toString(cbIPVia[0].isSelected()));p.setProperty(s[4]+".uas",Boolean.toString(cbIPMethod[1].isSelected()));
                p.setProperty(s[5]+".uac",Boolean.toString(cbPortVia[0].isSelected()));p.setProperty(s[5]+".uas",Boolean.toString(cbPortVia[1].isSelected()));
                p.setProperty(s[6]+".uac",Boolean.toString(cbIPFrom[0].isSelected()));p.setProperty(s[6]+".uas",Boolean.toString(cbIPFrom[1].isSelected()));
                p.setProperty(s[7]+".uac",Boolean.toString(cbPortFrom[0].isSelected()));p.setProperty(s[7]+".uas",Boolean.toString(cbPortFrom[1].isSelected()));
                p.setProperty(s[8]+".uac",Boolean.toString(cbIPTo[0].isSelected()));p.setProperty(s[8]+".uas",Boolean.toString(cbIPTo[1].isSelected()));
                p.setProperty(s[9]+".uac",Boolean.toString(cbPortTo[0].isSelected()));p.setProperty(s[9]+".uas",Boolean.toString(cbPortTo[1].isSelected()));
                p.setProperty(s[10]+".uac",Boolean.toString(cbIPContact[0].isSelected()));p.setProperty(s[10]+".uas",Boolean.toString(cbIPContact[1].isSelected()));
                p.setProperty(s[11]+".uac",Boolean.toString(cbPortContact[0].isSelected()));p.setProperty(s[11]+".uas",Boolean.toString(cbPortContact[1].isSelected()));
                p.setProperty(s[12]+".uac",Boolean.toString(cbTransportVia[0].isSelected()));p.setProperty(s[12]+".uas",Boolean.toString(cbTransportVia[1].isSelected()));
                p.setProperty(s[13]+".uac",Boolean.toString(cbTransportContact[0].isSelected()));p.setProperty(s[13]+".uas",Boolean.toString(cbTransportContact[1].isSelected()));
                p.setProperty(s[14]+".uac",Boolean.toString(cbCallNumberFrom[0].isSelected()));p.setProperty(s[14]+".uas",Boolean.toString(cbCallNumberFrom[1].isSelected()));
                p.setProperty(s[15]+".uac",Boolean.toString(cbCallNumberTo[0].isSelected()));p.setProperty(s[15]+".uas",Boolean.toString(cbCallNumberTo[1].isSelected()));
                p.setProperty(s[16]+".uac",Boolean.toString(cbPeerTagParam[0].isSelected()));p.setProperty(s[16]+".uas",Boolean.toString(cbPeerTagParam[1].isSelected()));
                p.setProperty(s[17]+".uac",Boolean.toString(cbCseq[0].isSelected()));p.setProperty(s[17]+".uas",Boolean.toString(cbCseq[1].isSelected()));
                p.setProperty(s[18]+".uac",Boolean.toString(cbBranch[0].isSelected()));p.setProperty(s[18]+".uas",Boolean.toString(cbBranch[1].isSelected()));
                p.setProperty(s[19]+".uac",Boolean.toString(cbIPTypeOrigin[0].isSelected()));p.setProperty(s[19]+".uas",Boolean.toString(cbIPTypeOrigin[1].isSelected()));
                p.setProperty(s[20]+".uac",Boolean.toString(cbIPOrigin[0].isSelected()));p.setProperty(s[20]+".uas",Boolean.toString(cbIPOrigin[1].isSelected()));
                p.setProperty(s[21]+".uac",Boolean.toString(cbIPTypeConnData[0].isSelected()));p.setProperty(s[21]+".uas",Boolean.toString(cbIPTypeConnData[1].isSelected()));
                p.setProperty(s[22]+".uac",Boolean.toString(cbIPConnData[0].isSelected()));p.setProperty(s[22]+".uas",Boolean.toString(cbIPConnData[1].isSelected()));
                p.setProperty(s[23]+".uac",Boolean.toString(cbPortMediDescription[0].isSelected()));p.setProperty(s[23]+".uas",Boolean.toString(cbPortMediDescription[1].isSelected()));

                p.setProperty(s[24]+".uac",Boolean.toString(cbViaL[0].isSelected()));p.setProperty(s[24]+".uas",Boolean.toString(cbViaL[1].isSelected()));
                p.setProperty(s[25]+".uac",Boolean.toString(cbFromL[0].isSelected()));p.setProperty(s[25]+".uas",Boolean.toString(cbFromL[1].isSelected()));
                p.setProperty(s[26]+".uac",Boolean.toString(cbToL[0].isSelected()));p.setProperty(s[26]+".uas",Boolean.toString(cbToL[1].isSelected()));
                p.setProperty(s[27]+".uac",Boolean.toString(cbCseqL[0].isSelected()));p.setProperty(s[27]+".uas",Boolean.toString(cbCseqL[1].isSelected()));
                p.setProperty(s[28]+".uac",Boolean.toString(cbCallIDL[0].isSelected()));p.setProperty(s[28]+".uas",Boolean.toString(cbTransportVia[1].isSelected()));

                try {
                    p.store(new FileOutputStream("main.properties"), "Config-File for SIPp xml converter");
                } catch(IOException ioe) {}
                Replace.this.dispose();
            }
        });

        uas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(p[0]);
                add(p[1],BorderLayout.CENTER);
                repaint();
                pack();
            }
        });

        uac.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(p[1]);
                add(p[0],BorderLayout.CENTER);
                repaint();
                pack();
            }
        });

        cbViaL[0].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbTransportVia[0].setEnabled(!cbViaL[0].isSelected());
                cbIPVia[0].setEnabled(!cbViaL[0].isSelected());
                cbPortVia[0].setEnabled(!cbViaL[0].isSelected());
            }
        });

        cbViaL[1].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbTransportVia[1].setEnabled(!cbViaL[1].isSelected());
                cbIPVia[1].setEnabled(!cbViaL[1].isSelected());
                cbPortVia[1].setEnabled(!cbViaL[1].isSelected());
            }
        });

        cbFromL[0].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbIPFrom[0].setEnabled(!cbFromL[0].isSelected());
                cbPortFrom[0].setEnabled(!cbFromL[0].isSelected());
            }
        });

        cbFromL[1].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbIPFrom[1].setEnabled(!cbFromL[1].isSelected());
                cbPortFrom[1].setEnabled(!cbFromL[1].isSelected());
            }
        });

        cbToL[0].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbServiceTo[0].setEnabled(!cbToL[0].isSelected());
                cbIPTo[0].setEnabled(!cbToL[0].isSelected());
                cbPortTo[0].setEnabled(!cbToL[0].isSelected());
                cbPeerTagParam[0].setEnabled(!cbToL[0].isSelected());
            }
        });

        cbToL[1].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbServiceTo[1].setEnabled(!cbToL[1].isSelected());
                cbIPTo[1].setEnabled(!cbToL[1].isSelected());
                cbPortTo[1].setEnabled(!cbToL[1].isSelected());
                cbPeerTagParam[1].setEnabled(!cbToL[1].isSelected());
            }
        });

        cbCseqL[0].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbCseq[0].setEnabled(!cbCseqL[0].isSelected());
            }
        });

        cbCseqL[1].addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                cbCseq[1].setEnabled(!cbCseqL[1].isSelected());
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                restoreState();
                dispose();
            }
        });

        bCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restoreState();
                dispose();
            }
        });
    }
}