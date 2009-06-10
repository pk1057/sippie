/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res.interfaces;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import javax.swing.JList;

/**
 *
 * @author christian
 */
public interface IOptionGUIElements {
    String distributions[] = {"none","fixed","uniform","normal","exponential","gamma",
                              "lambda","lognormal","negbin","pareto","weibull"};
    JPanel pSend = new JPanel(new GridLayout(11,2));
    JPanel pRecv = new JPanel(new GridLayout(20,2));
    JPanel pNop = new JPanel(new GridLayout(4,2));
    JPanel pSendCmd = new JPanel(new GridLayout(2,2));
    JPanel pRecvCmd = new JPanel(new GridLayout(3,2));
    JPanel pLabel = new JPanel(new GridLayout(2,2));
    JPanel pValue = new JPanel(new GridLayout(2,2));
    JPanel pPause = new JPanel(new GridLayout(8,2));
    JPanel pUnknown = new JPanel(new GridLayout(2,2));
    JPanel pAction = new JPanel(new BorderLayout());
        
    JTextField tfValue = new JTextField(10);
    JLabel lValue = new JLabel("Value");
    JTextField tfMilli = new JTextField(10);
    JLabel lMilli = new JLabel("Milliseconds");
    JTextField tfVariable = new JTextField(10);
    JLabel lVariable = new JLabel("Variable");
    JComboBox cbDist = new JComboBox(distributions);
    JLabel lDist = new JLabel("Distribution");
    JLabel lDistPar = new JLabel("Distribution Parameters");
    JTextField tfDistPar = new JTextField(10);
    JCheckBox[] cbCrlf = {new JCheckBox(),new JCheckBox(),new JCheckBox()};
    JLabel[]  lCrlf = {new JLabel("Empty Line"),new JLabel("Empty Line"),new JLabel("Empty Line")};
    JTextField[] tfNext = {new JTextField(10),new JTextField(10),new JTextField(10)};
    JLabel[] lNext =  {new JLabel("Next"),new JLabel("Next"),new JLabel("Next")};
    JCheckBox cbSanityCheck = new JCheckBox("",true);
    JLabel lSanityCheck = new JLabel("Sanity Check");
    JTextField tfRetrans = new JTextField(10);
    JLabel lRetrans = new JLabel("Retrans");
    JTextField[] tfStartRtd = {new JTextField(10),new JTextField(10),new JTextField(10)};
    JLabel[] lStartRtd = {new JLabel("Start Rtd"),new JLabel("Start Rtd"),new JLabel("Start Rtd")};
    JTextField[] tfRtd = {new JTextField(10),new JTextField(10),new JTextField(10)};
    JLabel[] lRtd = {new JLabel("Rtd"),new JLabel("Rtd"),new JLabel("Rtd")};
    JCheckBox[] cbRepeatRtd = {new JCheckBox(),new JCheckBox()};
    JLabel[] lRepeatRtd = {new JLabel("Repeat Rtd"),new JLabel("Repeat Rtd")};
    JTextField[] tfLost = {new JTextField(10),new JTextField(10)};
    JLabel[] lLost = {new JLabel("Lost"),new JLabel("Lost")};
    JTextField[] tfTest = {new JTextField(10),new JTextField(10)};
    JLabel[] lTest = {new JLabel("Test"),new JLabel("Test")};
    JTextField[] tfCounter = {new JTextField(10),new JTextField(10)};
    JLabel[] lCounter = {new JLabel("Counter"),new JLabel("Counter")};
    JTextField tfStartTxn = new JTextField(10);
    JLabel lStartTxn = new JLabel("Start Txn");
    JTextField tfResponse = new JTextField(10);
    JLabel lResponse = new JLabel("Response");
    JTextField tfRequest = new JTextField(10);
    JLabel lRequest = new JLabel("Request");
    JCheckBox cbOptional = new JCheckBox("",false);
    JLabel lOptional = new JLabel("Optional");
    JCheckBox cbRrs = new JCheckBox("",false);
    JLabel lRrs = new JLabel("Record Route Set");
    JCheckBox cbAuth = new JCheckBox();
    JLabel lAuth = new JLabel("Authentication");
    JTextField tfTimeout = new JTextField(10);
    JLabel lTimeout = new JLabel("Timeout");
    JTextField tfOnTimeout = new JTextField(10);
    JLabel lOnTimeout = new JLabel("On Tiemout");
    JCheckBox[] cbAction = {new JCheckBox(),new JCheckBox(),new JCheckBox()};
    JLabel[] lAction = {new JLabel("With Action"),new JLabel("With Action"),new JLabel("With Action"),new JLabel("With Action"),new JLabel("With Action")};
    JTextField tfChance = new JTextField(10);
    JList liAction = new JList();
    JLabel lChance = new JLabel("Chance");
    JCheckBox cbRegexMatch = new JCheckBox();
    JLabel lRegexMatch = new JLabel("Regex Match");
    JTextField tfResponseTxn = new JTextField(10);
    JLabel lResponseTxn = new JLabel("Response Txn");
    JTextField tfSrc = new JTextField(10);
    JLabel lSrc = new JLabel("Source");
    JTextField tfDest = new JTextField(10);
    JLabel lDest = new JLabel("Destination");
    JTextField tfID = new JTextField(10);
    JLabel lID = new JLabel("ID");
    JTextField[] tfAddOptions = {new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10),new JTextField(10), new JTextField(10)};
    JLabel[] lAddOptions = {new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options"),new JLabel("Additional Options")};
    JTextField tfTagName = new JTextField(10);
    JLabel lTagName = new JLabel("Tag Name");
    JTextField tfOptions = new JTextField(10);
    JLabel lOptions = new JLabel("Options");
}