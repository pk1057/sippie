/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.gjt.sp.jedit.Mode;
import org.gjt.sp.jedit.syntax.ModeProvider;
import org.gjt.sp.jedit.textarea.TextArea;

import converter.gui.command.CloseCommand;
import converter.gui.command.ConvertionCommand;
import converter.gui.command.CopyCommand;
import converter.gui.command.EditCommand;
import converter.gui.command.InsertCommand;
import converter.gui.command.OpenCommand;
import converter.gui.command.PasteCommand;
import converter.gui.command.ReplaceCommand;
import converter.gui.command.ReplaceOnTheFlyCommand;
import converter.gui.command.SaveCommand;
import converter.gui.command.SearchReplaceCommand;
import converter.gui.command.UndoCommand;
import converter.gui.command.UpdateCommand;
import converter.gui.command.ValidateCommand;
import converter.gui.filefilter.XmlFileFilter;
import converter.res.UndoRedoList;
import converter.res.interfaces.ICheckbox;
import converter.res.interfaces.IConstants;
import converter.res.interfaces.IOptionGUIElements;

/**
 *
 * @author christian
 */
public class SippXMLConverter extends JFrame implements ICheckbox, IOptionGUIElements, IConstants {

    static {
        pSend.add(new JPanel(new FlowLayout()).add(lRetrans));
            pSend.add(new JPanel(new FlowLayout()).add(tfRetrans));
            pSend.add(new JPanel(new FlowLayout()).add(lStartRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfStartRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lRepeatRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(cbRepeatRtd[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lCrlf[0]));
            pSend.add(new JPanel(new FlowLayout()).add(cbCrlf[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lLost[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfLost[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lNext[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfNext[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lTest[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfTest[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lCounter[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfCounter[0]));
            pSend.add(new JPanel(new FlowLayout()).add(lStartTxn));
            pSend.add(new JPanel(new FlowLayout()).add(tfStartTxn));
            pSend.add(new JPanel(new FlowLayout()).add(lAddOptions[0]));
            pSend.add(new JPanel(new FlowLayout()).add(tfAddOptions[0]));
            
            pRecv.add(new JPanel(new FlowLayout()).add(lRequest));
            pRecv.add(new JPanel(new FlowLayout()).add(tfRequest));
            pRecv.add(new JPanel(new FlowLayout()).add(lResponse));
            pRecv.add(new JPanel(new FlowLayout()).add(tfResponse));
            pRecv.add(new JPanel(new FlowLayout()).add(lOptional));
            pRecv.add(new JPanel(new FlowLayout()).add(cbOptional));
            pRecv.add(new JPanel(new FlowLayout()).add(lCrlf[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(cbCrlf[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lRrs));
            pRecv.add(new JPanel(new FlowLayout()).add(cbRrs));
            pRecv.add(new JPanel(new FlowLayout()).add(lAuth));
            pRecv.add(new JPanel(new FlowLayout()).add(cbAuth));
            pRecv.add(new JPanel(new FlowLayout()).add(lStartRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfStartRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lRepeatRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(cbRepeatRtd[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lLost[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfLost[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lTimeout));
            pRecv.add(new JPanel(new FlowLayout()).add(tfTimeout));
            pRecv.add(new JPanel(new FlowLayout()).add(lOnTimeout));
            pRecv.add(new JPanel(new FlowLayout()).add(tfOnTimeout));
            pRecv.add(new JPanel(new FlowLayout()).add(lAction[0]));
            pRecv.add(new JPanel(new FlowLayout()).add(cbAction[0]));
            pRecv.add(new JPanel(new FlowLayout()).add(lNext[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfNext[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lTest[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfTest[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lChance));
            pRecv.add(new JPanel(new FlowLayout()).add(tfChance));
            pRecv.add(new JPanel(new FlowLayout()).add(lCounter[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfCounter[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(lRegexMatch));
            pRecv.add(new JPanel(new FlowLayout()).add(cbRegexMatch));
            pRecv.add(new JPanel(new FlowLayout()).add(lResponseTxn));
            pRecv.add(new JPanel(new FlowLayout()).add(tfResponseTxn));
            pRecv.add(new JPanel(new FlowLayout()).add(lAddOptions[1]));
            pRecv.add(new JPanel(new FlowLayout()).add(tfAddOptions[1]));
            
            pNop.add(new JPanel(new FlowLayout()).add(lAction[1]));
            pNop.add(new JPanel(new FlowLayout()).add(cbAction[1]));
            pNop.add(new JPanel(new FlowLayout()).add(lStartRtd[2]));
            pNop.add(new JPanel(new FlowLayout()).add(tfStartRtd[2]));
            pNop.add(new JPanel(new FlowLayout()).add(lRtd[2]));
            pNop.add(new JPanel(new FlowLayout()).add(tfRtd[2]));
            pNop.add(new JPanel(new FlowLayout()).add(lAddOptions[2]));
            pNop.add(new JPanel(new FlowLayout()).add(tfAddOptions[2]));
            
            pSendCmd.add(new JPanel(new FlowLayout()).add(lDest));
            pSendCmd.add(new JPanel(new FlowLayout()).add(tfDest));
            pSendCmd.add(new JPanel(new FlowLayout()).add(lAddOptions[3]));
            pSendCmd.add(new JPanel(new FlowLayout()).add(tfAddOptions[3]));
            
            pRecvCmd.add(new JPanel(new FlowLayout()).add(lAction[2]));
            pRecvCmd.add(new JPanel(new FlowLayout()).add(cbAction[2]));
            pRecvCmd.add(new JPanel(new FlowLayout()).add(lSrc));
            pRecvCmd.add(new JPanel(new FlowLayout()).add(tfSrc));
            pRecvCmd.add(new JPanel(new FlowLayout()).add(lAddOptions[4]));
            pRecvCmd.add(new JPanel(new FlowLayout()).add(tfAddOptions[4]));
            
            pLabel.add(new JPanel(new FlowLayout()).add(lID));
            pLabel.add(new JPanel(new FlowLayout()).add(tfID));
            pLabel.add(new JPanel(new FlowLayout()).add(lAddOptions[5]));
            pLabel.add(new JPanel(new FlowLayout()).add(tfAddOptions[5]));
            
            pValue.add(new JPanel(new FlowLayout()).add(lValue));
            pValue.add(new JPanel(new FlowLayout()).add(tfValue));
            pValue.add(new JPanel(new FlowLayout()).add(lAddOptions[6]));
            pValue.add(new JPanel(new FlowLayout()).add(tfAddOptions[6]));
            
            pPause.add(new JPanel(new FlowLayout()).add(lMilli));
            pPause.add(new JPanel(new FlowLayout()).add(tfMilli));
            pPause.add(new JPanel(new FlowLayout()).add(lVariable));
            pPause.add(new JPanel(new FlowLayout()).add(tfVariable));
            pPause.add(new JPanel(new FlowLayout()).add(lDist));
            pPause.add(new JPanel(new FlowLayout()).add(cbDist));
            pPause.add(new JPanel(new FlowLayout()).add(lDistPar));
            pPause.add(new JPanel(new FlowLayout()).add(tfDistPar));
            pPause.add(new JPanel(new FlowLayout()).add(lCrlf[2]));
            pPause.add(new JPanel(new FlowLayout()).add(cbCrlf[2]));
            pPause.add(new JPanel(new FlowLayout()).add(lNext[2]));
            pPause.add(new JPanel(new FlowLayout()).add(tfNext[2]));
            pPause.add(new JPanel(new FlowLayout()).add(lSanityCheck));
            pPause.add(new JPanel(new FlowLayout()).add(cbSanityCheck));
            pPause.add(new JPanel(new FlowLayout()).add(lAddOptions[7]));
            pPause.add(new JPanel(new FlowLayout()).add(tfAddOptions[7]));
            
            pUnknown.add(new JPanel(new FlowLayout()).add(lTagName));
            pUnknown.add(new JPanel(new FlowLayout()).add(tfTagName));
            pUnknown.add(new JPanel(new FlowLayout()).add(lOptions));
            pUnknown.add(new JPanel(new FlowLayout()).add(tfOptions));
            
            JPanel center = new JPanel(new FlowLayout());
            JPanel south = new JPanel(new FlowLayout());

            center.add(lAction[3]);
            center.add(liAction);

            south.add(lAddOptions[8]);
            south.add(tfAddOptions[8]);

            pAction.add(center,BorderLayout.CENTER);
            pAction.add(south,BorderLayout.SOUTH);

            Properties p = new Properties();
            File f = new File("main.properties");
            String s[] = {"cb.service.method","cb.service.to","cb.ip.method","cb.port.method","cb.ip.via","cb.port.via",
            "cb.ip.from","cb.port.from","cb.ip.to","cb.port.to","cb.ip.contact","cb.port.contact","cb.transport.via",
            "cb.transport.contact","cb.callnumber.from","cb.callnumber.to","cb.peertagparam","cb.cseq","cb.branch",
            "cb.iptype.origin","cb.ip.origin","cb.iptype.conndata","cb.ip.conndata","cb.port.mediadescription",
            "cb.via.l","cb.from.l","cb.to.l","cb.cseq.l","cb.callid.l"
            };
            boolean b[][] = { {true,false},{true,false},{true,false},{true,false},{true,false},{true,false},{true,false},
                {true,false},{true,false},{true,false},{true,false},{true,false},{true,false},{true,false},{true,false},
                {true,false},{true,false},{true,false},{true,false},{true,false},{true,false},{true,false},{true,false},
                {true,false},
                {false,true},{false,true},{false,true},{false,true},{false,true}
            };
            try {
                p.load(new FileInputStream(f));
            } catch (IOException e) {}
            cbServiceMethod[0].setSelected(Boolean.parseBoolean(p.getProperty(s[0]+".uac",Boolean.toString(b[0][0]))));cbServiceMethod[0].setSelected(Boolean.parseBoolean(p.getProperty(s[0]+".uas",Boolean.toString(b[0][1]))));
            cbServiceTo[0].setSelected(Boolean.parseBoolean(p.getProperty(s[1]+".uac",Boolean.toString(b[1][0]))));cbServiceTo[1].setSelected(Boolean.parseBoolean(p.getProperty(s[1]+".uas",Boolean.toString(b[1][1]))));
            cbIPMethod[0].setSelected(Boolean.parseBoolean(p.getProperty(s[2]+".uac",Boolean.toString(b[2][0]))));cbIPMethod[1].setSelected(Boolean.parseBoolean(p.getProperty(s[2]+".uas",Boolean.toString(b[2][1]))));
            cbPortMethod[0].setSelected(Boolean.parseBoolean(p.getProperty(s[3]+".uac",Boolean.toString(b[3][0]))));cbPortMethod[1].setSelected(Boolean.parseBoolean(p.getProperty(s[3]+".uas",Boolean.toString(b[3][1]))));
            cbIPVia[0].setSelected(Boolean.parseBoolean(p.getProperty(s[4]+".uac",Boolean.toString(b[4][0]))));cbIPMethod[1].setSelected(Boolean.parseBoolean(p.getProperty(s[4]+".uas",Boolean.toString(b[4][1]))));
            cbPortVia[0].setSelected(Boolean.parseBoolean(p.getProperty(s[5]+".uac",Boolean.toString(b[5][0]))));cbPortVia[1].setSelected(Boolean.parseBoolean(p.getProperty(s[5]+".uas",Boolean.toString(b[5][1]))));
            cbIPFrom[0].setSelected(Boolean.parseBoolean(p.getProperty(s[6]+".uac",Boolean.toString(b[6][0]))));cbIPFrom[1].setSelected(Boolean.parseBoolean(p.getProperty(s[6]+".uas",Boolean.toString(b[6][1]))));
            cbPortFrom[0].setSelected(Boolean.parseBoolean(p.getProperty(s[7]+".uac",Boolean.toString(b[7][0]))));cbPortFrom[1].setSelected(Boolean.parseBoolean(p.getProperty(s[7]+".uas",Boolean.toString(b[7][1]))));
            cbIPTo[0].setSelected(Boolean.parseBoolean(p.getProperty(s[8]+".uac",Boolean.toString(b[8][0]))));cbIPTo[1].setSelected(Boolean.parseBoolean(p.getProperty(s[8]+".uas",Boolean.toString(b[8][1]))));
            cbPortTo[0].setSelected(Boolean.parseBoolean(p.getProperty(s[9]+".uac",Boolean.toString(b[9][0]))));cbPortTo[1].setSelected(Boolean.parseBoolean(p.getProperty(s[9]+".uas",Boolean.toString(b[9][1]))));
            cbIPContact[0].setSelected(Boolean.parseBoolean(p.getProperty(s[10]+".uac",Boolean.toString(b[10][0]))));cbIPContact[1].setSelected(Boolean.parseBoolean(p.getProperty(s[10]+".uas",Boolean.toString(b[10][1]))));
            cbPortContact[0].setSelected(Boolean.parseBoolean(p.getProperty(s[11]+".uac",Boolean.toString(b[11][0]))));cbPortContact[1].setSelected(Boolean.parseBoolean(p.getProperty(s[11]+".uas",Boolean.toString(b[11][1]))));
            cbTransportVia[0].setSelected(Boolean.parseBoolean(p.getProperty(s[12]+".uac",Boolean.toString(b[12][0]))));cbTransportVia[1].setSelected(Boolean.parseBoolean(p.getProperty(s[12]+".uas",Boolean.toString(b[12][1]))));
            cbTransportContact[0].setSelected(Boolean.parseBoolean(p.getProperty(s[13]+".uac",Boolean.toString(b[13][0]))));cbTransportContact[1].setSelected(Boolean.parseBoolean(p.getProperty(s[13]+".uas",Boolean.toString(b[13][1]))));
            cbCallNumberFrom[0].setSelected(Boolean.parseBoolean(p.getProperty(s[14]+".uac",Boolean.toString(b[14][0]))));cbCallNumberFrom[1].setSelected(Boolean.parseBoolean(p.getProperty(s[14]+".uas",Boolean.toString(b[14][1]))));
            cbCallNumberTo[0].setSelected(Boolean.parseBoolean(p.getProperty(s[15]+".uac",Boolean.toString(b[15][0]))));cbCallNumberTo[1].setSelected(Boolean.parseBoolean(p.getProperty(s[15]+".uas",Boolean.toString(b[15][1]))));
            cbPeerTagParam[0].setSelected(Boolean.parseBoolean(p.getProperty(s[16]+".uac",Boolean.toString(b[16][0]))));cbPeerTagParam[1].setSelected(Boolean.parseBoolean(p.getProperty(s[16]+".uas",Boolean.toString(b[16][1]))));
            cbCseq[0].setSelected(Boolean.parseBoolean(p.getProperty(s[17]+".uac",Boolean.toString(b[17][0]))));cbCseq[1].setSelected(Boolean.parseBoolean(p.getProperty(s[17]+".uas",Boolean.toString(b[17][1]))));
            cbBranch[0].setSelected(Boolean.parseBoolean(p.getProperty(s[18]+".uac",Boolean.toString(b[18][0]))));cbBranch[1].setSelected(Boolean.parseBoolean(p.getProperty(s[18]+".uas",Boolean.toString(b[18][1]))));
            cbIPTypeOrigin[0].setSelected(Boolean.parseBoolean(p.getProperty(s[19]+".uac",Boolean.toString(b[19][0]))));cbIPTypeOrigin[1].setSelected(Boolean.parseBoolean(p.getProperty(s[19]+".uas",Boolean.toString(b[19][1]))));
            cbIPOrigin[0].setSelected(Boolean.parseBoolean(p.getProperty(s[20]+".uac",Boolean.toString(b[20][0]))));cbIPOrigin[1].setSelected(Boolean.parseBoolean(p.getProperty(s[20]+".uas",Boolean.toString(b[20][1]))));
            cbIPTypeConnData[0].setSelected(Boolean.parseBoolean(p.getProperty(s[21]+".uac",Boolean.toString(b[21][0]))));cbIPTypeConnData[1].setSelected(Boolean.parseBoolean(p.getProperty(s[21]+".uas",Boolean.toString(b[21][1]))));
            cbIPConnData[0].setSelected(Boolean.parseBoolean(p.getProperty(s[22]+".uac",Boolean.toString(b[22][0]))));cbIPConnData[1].setSelected(Boolean.parseBoolean(p.getProperty(s[22]+".uas",Boolean.toString(b[22][1]))));
            cbPortMediDescription[0].setSelected(Boolean.parseBoolean(p.getProperty(s[23]+".uac",Boolean.toString(b[23][0]))));cbPortMediDescription[1].setSelected(Boolean.parseBoolean(p.getProperty(s[23]+".uas",Boolean.toString(b[23][1]))));

            cbViaL[0].setSelected(Boolean.parseBoolean(p.getProperty(s[24]+".uac",Boolean.toString(b[24][0]))));cbViaL[1].setSelected(Boolean.parseBoolean(p.getProperty(s[24]+".uas",Boolean.toString(b[24][1]))));
            cbFromL[0].setSelected(Boolean.parseBoolean(p.getProperty(s[25]+".uac",Boolean.toString(b[25][0]))));cbFromL[1].setSelected(Boolean.parseBoolean(p.getProperty(s[25]+".uas",Boolean.toString(b[25][1]))));
            cbToL[0].setSelected(Boolean.parseBoolean(p.getProperty(s[26]+".uac",Boolean.toString(b[26][0]))));cbToL[1].setSelected(Boolean.parseBoolean(p.getProperty(s[26]+".uas",Boolean.toString(b[26][1]))));
            cbCseqL[0].setSelected(Boolean.parseBoolean(p.getProperty(s[27]+".uac",Boolean.toString(b[27][0]))));cbCseqL[1].setSelected(Boolean.parseBoolean(p.getProperty(s[27]+".uas",Boolean.toString(b[27][1]))));
            cbCallIDL[0].setSelected(Boolean.parseBoolean(p.getProperty(s[28]+".uac",Boolean.toString(b[28][0]))));cbTransportVia[1].setSelected(Boolean.parseBoolean(p.getProperty(s[28]+".uas",Boolean.toString(b[28][1]))));
    }
    
    private JLabel statusBar = new JLabel("unnamed");
    private JButton bOpen = new JButton();
    private JButton bSave = new JButton();
    private JButton bSaveAs = new JButton();
    private JButton bReplacingRules = new JButton();
    private JButton bReplace = new JButton();
    private JButton bAdd = new JButton();
    private JButton bEdit = new JButton();
    private JButton bNew = new JButton();
    private JButton bReread = new JButton();
    private JButton bUndo = new JButton();
    private JButton bRedo = new JButton();
    private JButton bValidate = new JButton();
    private JToolBar control = new JToolBar();
    private JTabbedPane tpText = new JTabbedPane();
    private TextArea taUAC= TextArea.createTextArea();
    private TextArea taUAS= TextArea.createTextArea();
    
    private String pcapFile = "";
    private String scenarioName = "Default scenario";
    private String selectedCallID = "";
    private String newValue = "";
    private String saveUAC = "";
    private String saveUAS = "";
    private File workingDir = null;
    private boolean applyForAll = false;
    private boolean canceled = false;
    private boolean changeText = false;
    private boolean pushUndoTask = true;
    private boolean savedUAC = true;
    private boolean savedUAS = true;

    private Properties action;

    private UndoRedoList<String> undoUAS = new UndoRedoList<String>();
    private UndoRedoList<String> undoUAC = new UndoRedoList<String>();
    
    private JMenuBar mb = new JMenuBar();
    private JMenu mFile = new JMenu("File");
    private JMenuItem miEdit = new JMenuItem("Edit Line",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Edit16.gif")));
    private JMenuItem miClose = new JMenuItem("Quit");
    private JMenuItem miScenario = new JMenuItem("Scenario name");
    private JMenuItem miOpen = new JMenuItem("Open",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Open16.gif")));
    private JMenuItem miSave = new JMenuItem("Save",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Save16.gif")));
    private JMenuItem miSaveAs = new JMenuItem("Save As",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"SaveAs16.gif")));
    private JMenuItem miNew = new JMenuItem("New",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"New16.gif")));
    private JMenuItem miReread = new JMenuItem("Re-read",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Refresh16.gif")));
    private JMenu mAbout = new JMenu("About");
    private JMenu mOptions = new JMenu("Options");
    private JMenuItem miReplaceRules = new JMenuItem("Standard replacing rules",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Preferences16.gif")));
    private JMenuItem miReplace = new JMenuItem("Replace",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Replace16.gif")));
    private JMenuItem miCopy = new JMenuItem("Copy",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Copy16.gif")));
    private JMenuItem miPaste = new JMenuItem("Paste",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Paste16.gif")));
    private JMenu mEdit = new JMenu("Edit");
    private JMenuItem miInsertTag = new JMenuItem("Insert Tag",new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Add16.gif")));
    private JMenuItem miSearchReplace = new JMenuItem("Search/Replace");
    
    public SippXMLConverter() {
        makeMenu();
        makeLayout();
        addListeners();
        setTitle("Sipp XML Converter");
        setLocation((int)(screenWidth-getSize().width)/2,
                (int)(screenHeight-getSize().height)/2);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new SippXMLConverter().setVisible(true);
    }
    
    public void makeLayout() {
        JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
               
        bNew.setToolTipText("Create new xml-file of a pcap-file");
        bNew.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"New24.gif")));
        control.add(bNew);
        bReread.setEnabled(false);
        bReread.setToolTipText("re-read the pcap file and create new xml-file");
        bReread.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Refresh24.gif")));
        control.add(bReread);
        control.addSeparator();
        bUndo.setEnabled(false);
        bUndo.setToolTipText("Undo last task made");
        bUndo.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Undo24.gif")));
        control.add(bUndo);
        bRedo.setEnabled(false);
        bRedo.setToolTipText("Redo one task");
        bRedo.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Redo24.gif")));
        control.add(bRedo);
        control.addSeparator();
        bOpen.setToolTipText("Open xml-file to edit");
        bOpen.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Open24.gif")));
        control.add(bOpen);
        bSave.setToolTipText("Save converted xml-file");
        bSave.setActionCommand("save");
        bSave.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Save24.gif")));
        control.add(bSave);
        bSaveAs.setToolTipText("Save converted xml-file to specific file name");
        bSaveAs.setActionCommand("saveAs");
        bSaveAs.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"SaveAs24.gif")));
        control.add(bSaveAs);
        control.addSeparator();
        bReplacingRules.setToolTipText("Set the standard replacing rules");
        bReplacingRules.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Preferences24.gif")));
        control.add(bReplacingRules);
        bReplace.setToolTipText("Replace strings in this line");
        bReplace.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Replace24.gif")));
        control.add(bReplace);
        control.addSeparator();
        bEdit.setEnabled(true);
        bEdit.setToolTipText("Edit one line of the xml-file");
        bEdit.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Edit24.gif")));
        control.add(bEdit);
        bAdd.setEnabled(true);
        bAdd.setToolTipText("Insert a new tag into the xml-file");
        bAdd.setIcon(new ImageIcon(this.getToolkit().getImage("icons"+fsep+"Add24.gif")));
        control.add(bAdd);
        control.addSeparator();
        bValidate.setToolTipText("validate xml-file");
        bValidate.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("icons"+fsep+"Properties24.gif")));
        control.add(bValidate);
        
        Mode mode = new Mode("xml");
        mode.setProperty("file","xml.xml");
        ModeProvider.instance.addMode(mode);
        taUAC.getBuffer().setMode(mode); 
        taUAC.getGutter().setExpanded(true); 
        taUAS.getBuffer().setMode(mode); 
        taUAS.getGutter().setExpanded(true); 
        
        tpText.addTab("User Agent Client", taUAC);
        tpText.addTab("User Agent Server", taUAS);
        
        statusBar.setSize(statusBar.getPreferredSize().width,20);
        statusBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        statusBar.setText("unnamed");
        
        pCenter.add(tpText);
        add(control,BorderLayout.PAGE_START);
        add(tpText,BorderLayout.CENTER);
        add(statusBar,BorderLayout.SOUTH);
        this.pack();
        this.setMinimumSize(this.getPreferredSize());
    }
    
    public void makeMenu() {
        miSave.setActionCommand("save");
        miSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        miSaveAs.setActionCommand("saveAs");
        miSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        mFile.setMnemonic('f');
        mFile.add(miNew);
        mFile.add(miReread);
        mFile.addSeparator();
        mFile.add(miOpen);
        mFile.addSeparator();
        mFile.add(miSave);
        mFile.add(miSaveAs);
        mFile.addSeparator();
        mFile.add(miScenario);
        mFile.addSeparator();
        mFile.add(miClose);

        miInsertTag.setEnabled(true);
        miEdit.setEnabled(true);
        miCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        miPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        miSearchReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK));
        mEdit.setMnemonic('e');
        mEdit.add(miCopy);
        mEdit.add(miPaste);
        mEdit.addSeparator();
        mEdit.add(miInsertTag);
        mEdit.add(miEdit);
        mEdit.add(miSearchReplace);

        mOptions.setMnemonic('o');
        mOptions.add(miReplace);
        mOptions.add(miReplaceRules);

        mAbout.setMnemonic('a');

        mb.add(mFile);
        mb.add(mEdit);
        mb.add(mOptions);
        mb.add(mAbout);
        setJMenuBar(mb);
    }
    
    public void addListeners() {
        miSearchReplace.addActionListener(new SearchReplaceCommand(this));

        miCopy.addActionListener(new CopyCommand(this));
        miPaste.addActionListener(new PasteCommand(this));

        bValidate.addActionListener(new ValidateCommand(this));
        bReplace.addActionListener(new ReplaceOnTheFlyCommand(this));
        miReplace.addActionListener(new ReplaceOnTheFlyCommand(this));
        
        bReread.addActionListener(new ConvertionCommand(true,this));
        miReread.addActionListener(new ConvertionCommand(true,this));
        
        miNew.addActionListener(new ConvertionCommand(false,this));
        bNew.addActionListener(new ConvertionCommand(false,this));
        
        miOpen.addActionListener(new OpenCommand(this));
        bOpen.addActionListener(new OpenCommand(this));
        
        bReplacingRules.addActionListener(new ReplaceCommand(this));
        miReplaceRules.addActionListener(new ReplaceCommand(this));
        
        miEdit.addActionListener(new EditCommand(this));
        bEdit.addActionListener(new EditCommand(this));
        
        miInsertTag.addActionListener(new InsertCommand(this));
        bAdd.addActionListener(new InsertCommand(this));
        
        miClose.addActionListener(new CloseCommand(this,this,true));
        addWindowListener(new CloseCommand(this,this,true));
        
        bSave.addActionListener(new SaveCommand(this));
        miSave.addActionListener(new SaveCommand(this));
        bSaveAs.addActionListener(new SaveCommand(this));
        miSaveAs.addActionListener(new SaveCommand(this));

        taUAC.getBuffer().addBufferListener(new UndoCommand(this));
        taUAS.getBuffer().addBufferListener(new UndoCommand(this));
        
        tpText.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(tpText.getSelectedIndex()==0) {
                    if(savedUAC)
                        if(saveUAC.isEmpty())
                            statusBar.setText("unnamed");
                        else
                            statusBar.setText(saveUAC);
                    else
                        if(saveUAC.isEmpty())
                            statusBar.setText("unnamed *");
                        else
                            statusBar.setText(saveUAC+" *");
                    if(undoUAC.hasMoreUndoTasks()) bUndo.setEnabled(true);
                    else bUndo.setEnabled(false);
                    if(undoUAC.hasMoreRedoTasks()) bRedo.setEnabled(true);
                    else bRedo.setEnabled(false);
                    /*undoRedoStatus[2] = bUndo.isEnabled();
                    undoRedoStatus[3] = bRedo.isEnabled();
                    bUndo.setEnabled(undoRedoStatus[0]);
                    bRedo.setEnabled(undoRedoStatus[1]);*/
                }
                else {
                    if(savedUAS)
                        if(!saveUAC.isEmpty())
                            statusBar.setText(saveUAS);
                        else
                            statusBar.setText("unnamed");
                    else
                        if(!saveUAS.isEmpty())
                            statusBar.setText(saveUAS+" *");
                        else
                            statusBar.setText("unnamed *");
                    if(undoUAS.hasMoreUndoTasks()) bUndo.setEnabled(true);
                    else bUndo.setEnabled(false);
                    if(undoUAS.hasMoreRedoTasks()) bRedo.setEnabled(true);
                    else bRedo.setEnabled(false);
                    /*undoRedoStatus[0] = bUndo.isEnabled();
                    undoRedoStatus[1] = bRedo.isEnabled();
                    bUndo.setEnabled(undoRedoStatus[2]);
                    bRedo.setEnabled(undoRedoStatus[3]);*/
                }
            }
        });
        
        bUndo.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                System.out.println("bundo clicked");
                TextArea ta = tpText.getSelectedIndex()==0?taUAC:taUAS;
                pushUndoTask = false;
                int caret = ta.getCaretPosition();
                if(tpText.getSelectedIndex()==0) {
                    ta.setText(undoUAC.makeUndo(ta.getText()));
                    bUndo.setEnabled(undoUAC.hasMoreUndoTasks());
                }
                else {
                    ta.setText(undoUAS.makeUndo(ta.getText()));
                    bUndo.setEnabled(undoUAS.hasMoreUndoTasks());
                }
                bRedo.setEnabled(true);
                if(caret!=-1 && caret<ta.getBufferLength()) ta.setCaretPosition(caret);
                pushUndoTask = true;
            }
        });
        
        bRedo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextArea ta = tpText.getSelectedIndex()==0?taUAC:taUAS;
                pushUndoTask = false;
                int caret = ta.getCaretPosition();
                if(tpText.getSelectedIndex()==0) {
                    ta.setText(undoUAC.makeRedo(ta.getText()));
                    bRedo.setEnabled(undoUAC.hasMoreRedoTasks());
                }
                else {
                    ta.setText(undoUAS.makeRedo(ta.getText()));
                    bRedo.setEnabled(undoUAS.hasMoreRedoTasks());
                }
                bUndo.setEnabled(true);
                if(caret!=-1&&caret<ta.getBufferLength()) ta.setCaretPosition(caret);
                pushUndoTask = true;
            }
        });
        
        taUAC.addCaretListener(new UpdateCommand(this));
        taUAS.addCaretListener(new UpdateCommand(this));

        miScenario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String help = null;
                if((help = JOptionPane.showInputDialog(SippXMLConverter.this, "Please insert the name of the scenario!", scenarioName))!=null&&!help.equals(""))
                    scenarioName = help;
                else
                    JOptionPane.showMessageDialog(SippXMLConverter.this, "Invalid scenario name. Keeping old name.","Invalid scenario name",JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public void save(String actionCommand,TextArea ta) {
        boolean dontsave = false;
            if((saveUAC.isEmpty() && ta==taUAC) ||
                    (saveUAS.isEmpty() && ta==taUAS) || actionCommand.equals("saveAs")) {
                JFileChooser fc = new JFileChooser();
                if(workingDir!=null) fc.setCurrentDirectory(workingDir);
                fc.setAcceptAllFileFilterUsed(true);
                fc.setFileFilter(new XmlFileFilter());
                fc.showSaveDialog(SippXMLConverter.this);
                if(fc.getSelectedFile()!=null) {
                    try {
                        workingDir = fc.getCurrentDirectory();
                        if(taUAC==ta) {
                            saveUAC = fc.getSelectedFile().getCanonicalPath();
                            if(!saveUAC.endsWith(".xml")) saveUAC += ".xml";
                        }
                        else  {
                            saveUAS=fc.getSelectedFile().getCanonicalPath();
                            if(!saveUAS.endsWith(".xml")) saveUAS += ".xml";
                        }
                    }
                    catch(IOException ioe) { }
                }
                else dontsave = true;
            }
            if(((ta==taUAC && !saveUAC.isEmpty()) || (ta==taUAS && !saveUAS.isEmpty())) && !dontsave) {
                try {
                    if(taUAC==ta) writeFile(saveUAC,ta);
                    else writeFile(saveUAS,ta);
                }
                catch(IOException ioe) {
                    JOptionPane.showMessageDialog(SippXMLConverter.this, "Some problems occured while writing the output to "+(ta==taUAC?saveUAC:saveUAS), "I/O-Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(!dontsave) {
                if(ta==taUAC) {
                    statusBar.setText(saveUAC);
                    savedUAC = true;
                }
                else {
                    statusBar.setText(saveUAS);
                    savedUAS = true;
                }
            }
    }

    public int[] askForClosing() {
        int opUAC = 0;
        int opUAS = 0;

        if(!savedUAC) {
            opUAC = JOptionPane.showConfirmDialog(SippXMLConverter.this, "You made changes in UAC xml file, which were not saved. Do you want to save it now?","UAC not saved",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(opUAC==JOptionPane.YES_OPTION)
                save("",taUAC);
        }
        if(!savedUAS) {
            opUAS = JOptionPane.showConfirmDialog(SippXMLConverter.this, "You made changes in UAS xml file, which were not saved. Do you want to save it now?","UAS not saved",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(opUAS==JOptionPane.YES_OPTION)
                save("",taUAS);
        }
        return new int[] {opUAC, opUAS};
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean getCanceled() {
        return this.canceled;
    }

    public void setSelectedCallID(String selectedCallID) {
        this.selectedCallID = selectedCallID;
    }

    public String getSelectedCallID() {
        return this.selectedCallID;
    }

    public void setApplyForAll(boolean applyForAll) {
        this.applyForAll = applyForAll;
    }

    public boolean getApplyForAll() {
        return this.applyForAll;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public void setPcapFile(String pcapFile) {
        this.pcapFile = pcapFile;
    }

    public String getPcapFile() {
        return this.pcapFile;
    }

    public void setChangeText(boolean changeText) {
        this.changeText = changeText;
    }

    public boolean getChangeText() {
        return this.changeText;
    }

    public void setTpText(JTabbedPane tpText) {
        this.tpText = tpText;
    }

    public JTabbedPane getTpText() {
        return tpText;
    }

    public void setTaUAC(TextArea taUAC) {
        this.taUAC = taUAC;
    }

    public TextArea getTaUAC() {
        return taUAC;
    }

    public void setTaUAS(TextArea taUAS) {
        this.taUAS = taUAS;
    }

    public TextArea getTaUAS() {
        return taUAS;
    }

    public void setUndoUAS(UndoRedoList<String> undoUAS) {
        this.undoUAS = undoUAS;
    }

    public UndoRedoList<String> getUndoUAS() {
        return undoUAS;
    }

    public void setUndoUAC(UndoRedoList<String> undoUAC) {
        this.undoUAC = undoUAC;
    }

    public UndoRedoList<String> getUndoUAC() {
        return undoUAC;
    }

    public void setPushUndoTask(boolean pushUndoTask) {
        this.pushUndoTask = pushUndoTask;
    }

    public boolean isPushUndoTask() {
        return pushUndoTask;
    }

    public boolean isSavedUAC() {
        return savedUAC;
    }

    public void setSavedUAC(boolean savedUAC) {
        this.savedUAC = savedUAC;
    }

    public boolean isSavedUAS() {
        return savedUAS;
    }

    public void setSavedUAS(boolean savedUAS) {
        this.savedUAS = savedUAS;
    }

    public JLabel getStatusBar() {
        return statusBar;
    }

    public void setStatusBar(JLabel statusBar) {
        this.statusBar = statusBar;
    }

    public String getSaveUAC() {
        return saveUAC;
    }

    public void setSaveUAC(String saveUAC) {
        this.saveUAC = saveUAC;
    }

    public String getSaveUAS() {
        return saveUAS;
    }

    public void setSaveUAS(String saveUAS) {
        this.saveUAS = saveUAS;
    }

    public JButton getBUndo() {
        return bUndo;
    }

    public void setBUndo(JButton bUndo) {
        this.bUndo = bUndo;
    }

    public JButton getBRedo() {
        return bRedo;
    }

    public void setBRedo(JButton bRedo) {
        this.bRedo = bRedo;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public JButton getBReread() {
        return bReread;
    }

    public void setBReread(JButton bReread) {
        this.bReread = bReread;
    }

    public JMenuItem getMiInsertTag() {
        return miInsertTag;
    }

    public void setMiInsertTag(JMenuItem miInsertTag) {
        this.miInsertTag = miInsertTag;
    }

    public JButton getBAdd() {
        return bAdd;
    }

    public void setBAdd(JButton bAdd) {
        this.bAdd = bAdd;
    }

    public JButton getBEdit() {
        return bEdit;
    }

    public void setBEdit(JButton bEdit) {
        this.bEdit = bEdit;
    }

    public JMenuItem getMiEdit() {
        return miEdit;
    }

    public void setMiEdit(JMenuItem miEdit) {
        this.miEdit = miEdit;
    }

    public File getWorkingDir() {
        return workingDir;
    }

    public void setWorkingDir(File workingDir) {
        this.workingDir = workingDir;
    }

    public Properties getProperties() {
        return action;
    }

    public void setProperties(Properties action) {
        this.action = action;
    }

    public void resetOptionGUIElements()  {
        tfValue.setText("");
        tfMilli.setText("");
        tfVariable.setText("");
        cbDist.setSelectedItem(0);
        tfDistPar.setText("");
        for(JCheckBox cb : cbCrlf) cb.setSelected(false);
        for(JTextField tf : tfNext) tf.setText("");
        cbSanityCheck.setSelected(true);
        tfRetrans.setText("");
        for(JTextField tf : tfStartRtd) tf.setText("");
        for(JTextField tf : tfRtd) tf.setText("");
        for(JCheckBox cb : cbRepeatRtd) cb.setSelected(false);
        for(JTextField tf : tfLost) tf.setText("");
        for(JTextField tf : tfTest) tf.setText("");
        for(JTextField tf : tfCounter) tf.setText("");
        tfStartTxn.setText("");
        tfResponse.setText("");
        tfRequest.setText("");
        cbOptional.setSelected(false);
        cbRrs.setSelected(false);
        cbAuth.setSelected(false);
        tfTimeout.setText("");
        tfOnTimeout.setText("");
        FileInputStream fis;
        try {
            fis = new FileInputStream("action.properties");
            action = new Properties();
            action.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SippXMLConverter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ioe) {
            
        }
        Vector<String> comboBoxContent = new Vector<String>();
        if(action!=null)
            for(Object s : action.keySet()) comboBoxContent.add((String)s);

        System.out.println(comboBoxContent);

        liAction.setListData(comboBoxContent);
        tfChance.setText("");
        cbRegexMatch.setSelected(false);
        tfResponseTxn.setText("");
        tfSrc.setText("");
        tfDest.setText("");
        tfID.setText("");
        for(JTextField tf : tfAddOptions) tf.setText("");
        tfTagName.setText("");
        tfOptions.setText("");
    }
    
    public void writeFile(String file,TextArea ta) throws IOException {
        FileWriter fw = new FileWriter(file);
        
        fw.write(ta.getText());
        fw.close();
    }
    
    public String readList(String file,TextArea ta) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String tempString = "";
        StringBuffer xmlData = new StringBuffer();
               
        while((tempString=br.readLine())!=null) xmlData.append(tempString).append("\n");
            
        pushUndoTask=false;
        String erg = new String(xmlData);
        ta.setText(erg);
        pushUndoTask=true;
        ta.setCaretPosition(0);
        return erg;
    }

   
}