/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.ChangeValue;
import converter.gui.dialog.EditTag;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class EditCommand implements ActionListener {

    JFrame parent;

    public EditCommand(JFrame parent) {
        this.parent = parent;
    }

    @SuppressWarnings("empty-statement")
    public void actionPerformed(ActionEvent e) {
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        TextArea ta = tpText.getSelectedIndex()==0?((SippXMLConverter)parent).getTaUAC():((SippXMLConverter)parent).getTaUAS();
        JLabel statusBar = ((SippXMLConverter)parent).getStatusBar();
        String saveUAC = ((SippXMLConverter)parent).getSaveUAC();
        String saveUAS = ((SippXMLConverter)parent).getSaveUAS();
        String newValue = ((SippXMLConverter)parent).getNewValue();
        int pos = ta.getCaretPosition();

        if(((SippXMLConverter)parent).getChangeText()) {
            StringTokenizer st = new StringTokenizer(ta.getBuffer().getLineText(ta.getCaretLine()),":=");

            String key = st.nextToken();
            String value="";
            while(st.hasMoreTokens())
                value += st.nextToken();

            new ChangeValue(key, value, parent).setVisible(true);
            newValue = ((SippXMLConverter)parent).getNewValue();
            if(!((SippXMLConverter)parent).getCanceled()) {
                StringBuffer temp = new StringBuffer();
                if(!((SippXMLConverter)parent).getApplyForAll()) {
                    temp.append(ta.getText(0,ta.getLineStartOffset(ta.getCaretLine()))+
                            key+":"+newValue+ta.getText(ta.getLineEndOffset(ta.getCaretLine()),ta.getBufferLength()-ta.getLineEndOffset(ta.getCaretLine())));
                }
                else {
                    for(int i=0;i<ta.getLineCount();i++) {
                        if(!ta.getLineText(i).matches("^[a-zA-Z].*$"))
                            temp.append(ta.getLineText(i)).append("\n");
                        else {
                            StringTokenizer stTemp = new StringTokenizer(ta.getBuffer().getLineText(i),":=");
                            String tempKey = stTemp.nextToken();
                            if(tempKey.equals(key))
                                temp.append(key+":"+newValue);
                            else
                                temp.append(ta.getLineText(i)).append("\n");
                        }
                    }
                }
                if(tpText.getSelectedIndex()==0) ((SippXMLConverter)parent).getUndoUAC().pushTask(ta.getText());
                else ((SippXMLConverter)parent).getUndoUAS().pushTask(ta.getText());
                ((SippXMLConverter)parent).setPushUndoTask(false);
                ta.setText(new String(temp));
                ((SippXMLConverter)parent).setPushUndoTask(true);
                if(tpText.getSelectedIndex()==0)
                    ((SippXMLConverter)parent).setSavedUAC(false);
                else
                    ((SippXMLConverter)parent).setSavedUAS(false);
                if((tpText.getSelectedIndex()==0&&saveUAC.isEmpty())||(tpText.getSelectedIndex()==1&&saveUAS.isEmpty()))
                    statusBar.setText("unnamed *");
                else
                    statusBar.setText((tpText.getSelectedIndex()==0?saveUAC:saveUAS)+" *");
                ((SippXMLConverter)parent).getBUndo().setEnabled(true);
            }
            else
                ((SippXMLConverter)parent).setCanceled(false);
        }
        else {
            ((SippXMLConverter)parent).resetOptionGUIElements();
            int lineno = ta.getCaretLine();
            String line = ta.getLineText(ta.getCaretLine());
            Vector<String> regexps = new Vector<String>();
            String matcher=ta.getLineText(lineno+1);
            int i=1;
            for(;!matcher.matches("^.*</[ ]*action[ ]*>.*$")&&(lineno+i)<ta.getLastPhysicalLine();matcher=ta.getLineText(lineno+i+1),i++) {
                System.out.println("match");
                regexps.add(matcher.trim());
            }
            String before = line.replaceFirst("(^.*)(<[^/]+>)(.*$)", "$1");
            String after = "";
            if(!ta.getLineText(ta.getCaretLine()).contains("action"))
                after = line.replaceFirst("(^.*)(<[^/]+>)(.*$)", "$3");

            System.out.println("AFTER: "+after);


            System.out.println("Regexps: "+regexps);

            new EditTag(ta.getLineText(ta.getCaretLine()), parent, regexps).setVisible(true);

            newValue = ((SippXMLConverter)parent).getNewValue();
            System.out.println(newValue);
            String replace = "";
            if(newValue.contains("</recv>")) replace = "</recv>";
            else if(newValue.contains("</nop>")) replace = "</nop>";
            else if(newValue.contains("</recvCmd>")) replace = "</recvCmd>";

            int j = 0;
            if(!replace.isEmpty())
                for(;!ta.getLineText(ta.getCaretLine()+j).contains(replace);j++);

            System.out.println(j);

            if(!((SippXMLConverter)parent).getCanceled()) {
                StringBuffer temp = new StringBuffer(ta.getText(0,ta.getLineStartOffset(ta.getCaretLine())));
                temp.append(before).append(((SippXMLConverter)parent).getNewValue()).append(after).append("\n");
                System.out.println("lineno: "+ta.getCaretLine()+" i:"+i);
                if(!ta.getLineText(ta.getCaretLine()).contains("action")) i= j==0 ? 1 : j+1;
                temp.append(ta.getText(ta.getLineEndOffset(ta.getCaretLine()+i-1),ta.getBufferLength()-ta.getLineEndOffset(ta.getCaretLine()+i-1)));
                if(tpText.getSelectedIndex()==0) ((SippXMLConverter)parent).getUndoUAC().pushTask(ta.getText());
                else ((SippXMLConverter)parent).getUndoUAS().pushTask(ta.getText());
                ((SippXMLConverter)parent).setPushUndoTask(false);
                ta.setText(new String(temp));
                ((SippXMLConverter)parent).setPushUndoTask(true);
                if(tpText.getSelectedIndex()==0)
                    ((SippXMLConverter)parent).setSavedUAC(false);
                else
                    ((SippXMLConverter)parent).setSavedUAS(false);
                if((tpText.getSelectedIndex()==0&&saveUAC.isEmpty())||(tpText.getSelectedIndex()==1&&saveUAS.isEmpty()))
                    statusBar.setText("unnamed *");
                else
                    statusBar.setText((tpText.getSelectedIndex()==0?saveUAC:saveUAS)+" *");
                ((SippXMLConverter)parent).getBUndo().setEnabled(true);
            }
            ((SippXMLConverter)parent).setCanceled(false);
        }
        ta.setCaretPosition(pos);
    }
}