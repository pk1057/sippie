/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import org.gjt.sp.jedit.textarea.TextArea;

/**
 *
 * @author Christian
 */
public class UpdateCommand implements CaretListener {

    JFrame parent;

    public UpdateCommand(JFrame parent) {
        this.parent = parent;
    }

    /**
     * Does not work as expected. If somebody wants to make it work, good luck!
     * This method should deactivate buttons, if it is not possible to insert a tag
     * or change a line.
     * @param e
     */

    @SuppressWarnings("empty-statement")
    public void caretUpdate(CaretEvent e) {
        JTabbedPane tpText = ((SippXMLConverter)parent).getTpText();
        TextArea taUAC = ((SippXMLConverter)parent).getTaUAC();
        TextArea taUAS = ((SippXMLConverter)parent).getTaUAS();
        TextArea ta = tpText.getSelectedIndex()==0?taUAC:taUAS;
        JMenuItem miInsertTag = ((SippXMLConverter)parent).getMiInsertTag();
        JButton bAdd = ((SippXMLConverter)parent).getBAdd();
        JButton bEdit = ((SippXMLConverter)parent).getBEdit();
        JMenuItem miEdit = ((SippXMLConverter)parent).getMiEdit();
        String line = ta.getBuffer().getLineText(ta.getCaretLine());
        StringTokenizer st = new StringTokenizer(line,":=");
        String key = st.hasMoreTokens()?st.nextToken():"";
        if(line.matches("^[A-Za-z].*$")) {
            /*miInsertTag.setEnabled(true);
            bAdd.setEnabled(true);
            bEdit.setEnabled(true);
            miEdit.setEnabled(true);*/
            ((SippXMLConverter)parent).setChangeText(true);
        }
        else {
            /*boolean beginTagBefore = false, endTagAfter = false;
            String matcher = "";
            int beforeLineOffset=0, afterEndOffset=0;
                    try {
            for(;!matcher.matches("^.*<+>$")&&ta.getCaretLine()-beforeLineOffset>=0;beforeLineOffset++,matcher=ta.getBuffer().getLineText(ta.getCaretLine()-beforeLineOffset));
            System.out.println(new Date()+" matcher1: "+matcher+" i: "+beforeLineOffset);
            for(;!matcher.matches("^.*<.+>$")&&afterEndOffset+ta.getCaretLine()<ta.getLastPhysicalLine();afterEndOffset++,matcher=ta.getBuffer().getLineText(ta.getCaretLine()+afterEndOffset));
            System.out.println("matcher2: "+matcher+" i: "+afterEndOffset);
            } catch(ArrayIndexOutOfBoundsException aioobe) {}
            if(matcher.matches("^.*<[a-zA-Z0-9=\"\' ]+/>$") || ta.getBuffer().getLineText(ta.getCaretLine()).matches("^.*<[a-zA-Z0-9=\"\' ]+/>$"))
                beginTagBefore = endTagAfter = true;
            else {
                matcher = ta.getBuffer().getLineText(ta.getCaretLine()-beforeLineOffset);
                if(matcher.matches("^.*<\\w+[^/]+>.*$")) beginTagBefore = true;
                matcher = ta.getBuffer().getLineText(ta.getCaretLine()+afterEndOffset);
                if(matcher.matches("^.*</[a-zA-Z]+>$")) endTagAfter = true;
            }*/

            if(line.matches("^.*<\\w+[^/]+>.*$")) {
                /*bEdit.setEnabled(true);
                miEdit.setEnabled(true);*/
                ((SippXMLConverter)parent).setChangeText(false);
            }
            /*else {
                bEdit.setEnabled(false);
                miEdit.setEnabled(false);
            }
            if(true)  {
                miInsertTag.setEnabled(true);
                bAdd.setEnabled(true);
            }*/

        }

    }
}