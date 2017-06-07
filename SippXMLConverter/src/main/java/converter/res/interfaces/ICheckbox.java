/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res.interfaces;

import java.util.Properties;
import javax.swing.JCheckBox;

/**
 *
 * @author christian
 */
public interface ICheckbox {

    JCheckBox cbServiceMethod[] = {new JCheckBox("Service",true),new JCheckBox("Service",false)};
    JCheckBox cbServiceTo[] = {new JCheckBox("Service",true),new JCheckBox("Service",false)};
    JCheckBox cbIPMethod[] = {new JCheckBox("IP/Domain",true),new JCheckBox("IP/Domain",false)};
    JCheckBox cbPortMethod[] = {new JCheckBox("Port",true),new JCheckBox("Port",false)};
    JCheckBox cbIPVia[] = {new JCheckBox("IP/Domain",true),new JCheckBox("IP/Domain",false)};
    JCheckBox cbPortVia[] = {new JCheckBox("Port",true),new JCheckBox("Port",false)};
    JCheckBox cbIPFrom[] = {new JCheckBox("IP/Domain",true),new JCheckBox("IP/Domain",false)};
    JCheckBox cbPortFrom[] = {new JCheckBox("Port",true),new JCheckBox("Port",false)};
    JCheckBox cbIPTo[] = {new JCheckBox("IP/Domain",true),new JCheckBox("IP/Domain",false)};
    JCheckBox cbPortTo[] = {new JCheckBox("Port",true),new JCheckBox("Port",false)};
    JCheckBox cbIPContact[] = {new JCheckBox("IP/Domain",true),new JCheckBox("IP/Domain",false)};
    JCheckBox cbPortContact[] = {new JCheckBox("Port",true),new JCheckBox("Port",false)};
    JCheckBox cbTransportVia []= {new JCheckBox("Transport",true),new JCheckBox("Transport",false)};
    JCheckBox cbTransportContact[] = {new JCheckBox("Transport",true),new JCheckBox("Transport",false)};
    JCheckBox cbCallNumberFrom[] = {new JCheckBox("Call Number",true),new JCheckBox("Call Number",false)};
    JCheckBox cbCallNumberTo[] = {new JCheckBox("Call Number",true),new JCheckBox("Call Number",false)};
    JCheckBox cbPeerTagParam[] = {new JCheckBox("Peer Tag Param",true),new JCheckBox("Peer Tag Param",false)};
    JCheckBox cbCseq[] = {new JCheckBox("Cseq",true),new JCheckBox("Cseq",false)};
    JCheckBox cbBranch[] = {new JCheckBox("Branch",true),new JCheckBox("Branch",false)};
    JCheckBox cbIPTypeOrigin[] = {new JCheckBox("Origin IP Type",true),new JCheckBox("Origin IP Type",false)};
    JCheckBox cbIPOrigin[] = {new JCheckBox("Origin IP",true),new JCheckBox("Origin IP",false)};
    JCheckBox cbIPTypeConnData[] = {new JCheckBox("Connection Data IP Type",true),new JCheckBox("Connection Data IP Type",false)};
    JCheckBox cbIPConnData[] = {new JCheckBox("Connection Data IP",true),new JCheckBox("Connection Data IP",false)};
    JCheckBox cbPortMediDescription[] = {new JCheckBox("Media Description Port",true),new JCheckBox("Media Description Port",false)};
    
    JCheckBox cbViaL[] = {new JCheckBox("Last Via",false),new JCheckBox("Last Via",true)};
    JCheckBox cbFromL[] = {new JCheckBox("Last From",false),new JCheckBox("Last From",true)};
    JCheckBox cbToL[] = {new JCheckBox("Last To",false),new JCheckBox("Last To",true)};
    JCheckBox cbCseqL[] = {new JCheckBox("Last Cseq",false),new JCheckBox("Last Cseq",true)};
    JCheckBox cbCallIDL[] = {new JCheckBox("Last Call-ID",false),new JCheckBox("Last Call-ID",true)};
}
