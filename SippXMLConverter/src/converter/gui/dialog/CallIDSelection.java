/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.gui.dialog;

import converter.gui.SippXMLConverter;
import converter.op.Operation;
import converter.res.CallSelection;
import converter.res.interfaces.IConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Christian
 */
public class CallIDSelection extends JDialog implements IConstants {

    private JFrame parent;

    private Hashtable<String, CallSelection> callIDs = new Hashtable<String, CallSelection>();
    private DefaultMutableTreeNode root = new DefaultMutableTreeNode("Select the Call-ID for generating the XML:");
    private JScrollPane treeView;
    private JButton bOK = new JButton("OK");
    private JTree lCallID;

    public CallIDSelection(JFrame parent) {
        this.parent = parent;

        callIDs = Operation.getCalls(((SippXMLConverter)parent).getPcapFile());
        makeLayout();
        addListeners();
        setModal(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Point location = parent.getLocation();
        int width = (parent.getSize().width-this.getWidth())/2;
        int height = (parent.getSize().height-this.getHeight())/2;
        setLocation(width<0?(int)(screenWidth-this.getWidth())/2:width+location.x,
                height<0?(int)(screenHeight-this.getHeight())/2:height+location.y);
    }

    public void makeLayout() {

        lCallID = new JTree(root);
        DefaultTreeSelectionModel selModel = new DefaultTreeSelectionModel();
        selModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        lCallID.setSelectionModel(selModel);

        for(String s : callIDs.keySet()) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(s);
            DefaultMutableTreeNode requestLine = new DefaultMutableTreeNode(callIDs.get(s).getRequestLine());
            DefaultMutableTreeNode sourceIP = new DefaultMutableTreeNode("Source-IP: "+callIDs.get(s).getSourceIP());
            DefaultMutableTreeNode destIP = new DefaultMutableTreeNode("Destination-IP: "+callIDs.get(s).getDestIP());
            root.add(parentNode);
            

            parentNode.add(requestLine);
            parentNode.add(sourceIP);
            parentNode.add(destIP);
        }

        
        expandAll(lCallID,new TreePath(root));
        lCallID.setSelectionPath(new TreePath(((DefaultMutableTreeNode)root.getChildAt(0)).getPath()));
        
        treeView = new JScrollPane(lCallID);

        JPanel pButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pButton.add(bOK);

        add(treeView, BorderLayout.CENTER);
        add(pButton, BorderLayout.SOUTH);

        pack();
    }

    private void expandAll(JTree tree, TreePath parent) {
        // Traverse children
        TreeNode node = (TreeNode)parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e=node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode)e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path);
            }
        }
        tree.expandPath(parent);
    }


    public void addListeners() {
        lCallID.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                if(e.getPath().getPathCount()==1)
                    lCallID.setSelectionPath(e.getOldLeadSelectionPath());
                if(e.getPath().getPathCount()==3)
                    lCallID.setSelectionPath(e.getOldLeadSelectionPath());
            }
        });
        lCallID.addTreeExpansionListener(new TreeExpansionListener() {
            public void treeCollapsed(TreeExpansionEvent e) {
                if(e.getPath().getLastPathComponent()==root)
                    lCallID.expandPath(new TreePath(root));
            }
            public void treeExpanded(TreeExpansionEvent e) {

            }
        });
        bOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CallIDSelection.this.dispose();
                ((SippXMLConverter)parent).setSelectedCallID((String)((DefaultMutableTreeNode)lCallID.getSelectionPath().getLastPathComponent()).getUserObject());
            }
        });
    }
    }