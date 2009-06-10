/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.command;

import converter.gui.SippXMLConverter;
import converter.gui.dialog.Validate;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.gjt.sp.jedit.textarea.TextArea;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Christian
 */
public class ValidateCommand implements ActionListener {

    private JFrame parent;

    private String type[];
    private String message[];
    private Window w;

    public ValidateCommand(JFrame parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        TextArea taUAC = ((SippXMLConverter)parent).getTaUAC();
        TextArea taUAS = ((SippXMLConverter)parent).getTaUAS();
        type = new String[2];type[0]="";type[1]="";
        message = new String[2];message[0]="";message[1]="";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler(0));
            builder.parse(new InputSource(new StringReader(taUAC.getText())));
        }
        catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch(SAXException se) {
            se.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler(1));
            builder.parse(new InputSource(new StringReader(taUAS.getText())));
        }
        catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        catch(SAXException se) {
            se.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        if(w==null||!w.isShowing())
            w = new Validate(type,message,parent);
        else
            ((Validate)w).updatePane(type,message);
    }
    class SimpleErrorHandler implements ErrorHandler {

        int i = 0;

        public SimpleErrorHandler(int i) {
            this.i = i;
        }

        public void warning(SAXParseException e) throws SAXException {
            type[i] = "Warning@"+e.getLineNumber()+":"+e.getColumnNumber();
            message[i] = e.getMessage();
        }

        public void error(SAXParseException e) throws SAXException {
            type[i] = "Error@"+e.getLineNumber()+":"+e.getColumnNumber();
            message[i] = e.getMessage();
        }

        public void fatalError(SAXParseException e) throws SAXException {
            type[i] = "Fatal Error@"+e.getLineNumber()+":"+e.getColumnNumber();
            message[i] = e.getMessage();
        }
    }
}