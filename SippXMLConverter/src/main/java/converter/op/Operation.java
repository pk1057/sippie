/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter.op;

import converter.res.CallSelection;
import converter.res.SequentialMap;
import converter.res.interfaces.ICheckbox;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;
import org.jdom.CDATA;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.UdpPacket;
import org.pcap4j.packet.UdpPacket.UdpHeader;

/**
 *
 * @author christian
 */
public class Operation implements ICheckbox {

    private static boolean rrs = false;

    private static ArrayList<SequentialMap<String, String>> readIn(String pcap, String callID) {
        PcapHandle captor = null;
        ArrayList<SequentialMap<String, String>> packets = new ArrayList<SequentialMap<String, String>>();

        try {
            captor = Pcaps.openOffline(pcap);
            Packet p;
            UdpPacket p1;
            String data;
            do {
                p = captor.getNextPacket();
                p1 = p.get(UdpPacket.class);
                if (p1 != null) {
                    data = new String(p1.getPayload().getRawData());
                } else {
                    data = "";
                }
            } while (p1 == null || data.isEmpty() || !getCallID(data).equals(callID));

            String tag = getTag(data);

            while (p != null) {
                data = new String(p1.getPayload().getRawData());
                StringTokenizer enter = new StringTokenizer(data, "\n");
                SequentialMap<String, String> sm = new SequentialMap<String, String>();
                OUTER:
                for (int i = 0; enter.hasMoreTokens(); i++) {
                    String nextToken = enter.nextToken();
                    while (nextToken.length() == 1) {      // discard line
                        if (enter.hasMoreTokens()) {
                            nextToken = enter.nextToken();
                        } else {
                            continue OUTER;
                        }
                    }
                    if (i == 0) {
                        sm.put(nextToken, null);
                    } else {
                        StringTokenizer colon = new StringTokenizer(nextToken, ":=", true);
                        String key = colon.nextToken();
                        StringBuffer value = new StringBuffer(colon.nextToken());

                        while (colon.hasMoreTokens()) {
                            value.append(colon.nextToken());
                        }
                        sm.put(key, new String(value));
                    }
                }
                packets.add(sm);
                do {
                    p = captor.getNextPacket();
                    p1 = p.get(UdpPacket.class);
                    if (p1 != null) {
                        data = new String(p1.getPayload().getRawData());
                    } else {
                        data = "";
                    }
                } while (p1 == null || data.isEmpty() || !getTag(data).equals(tag));
            }
        } catch (Exception e) {

        }
        return packets;
    }

    @SuppressWarnings("unchecked")
    public static void convert(String scenarioName, String pcap, String callID) throws IOException {
        ArrayList<SequentialMap<String, String>> packets = readIn(pcap, callID);

        Format f = Format.getPrettyFormat();
        f.setExpandEmptyElements(true);
        f.setTextMode(Format.TextMode.PRESERVE);
        f.setLineSeparator(System.getProperty("line.separator"));
        Document doc;
        DocType docType1 = new DocType("scenario", "sipp.dtd");
        DocType docType2 = new DocType("scenario", "sipp.dtd");
        XMLOutputter out = new XMLOutputter(f);
        FileWriter fw;
        String xml;
        StringWriter sw;

        Element root = new Element("scenario");
        root.setAttribute("name", scenarioName);

        for (SequentialMap<String, String> sm : packets) {
            if (!sm.isEmpty()) {
                String head = sm.firstKey();
                StringTokenizer st = new StringTokenizer(head, " ");
                String method = st.nextToken();
                if (method.startsWith("SIP")) {
                    method = st.nextToken();
                    Element temp = new Element("recv");
                    temp.setAttribute("response", method);
                    System.out.println(sm);
                    StringTokenizer st1 = new StringTokenizer(sm.getKey(0), " ");
                    System.out.println(st1.nextToken());
                    String temp1 = st1.nextToken();
                    System.out.println(temp1);
                    if (temp1.startsWith("2")) {
                        temp.setAttribute("rrs", "true");
                        rrs = true;
                    }
                    root.addContent(temp);
                } else {
                    String s = replaceText(sm, 0);
                    if (s.contains("authentication")) {
                        List<Element> children = root.getChildren();
                        Element temp = null;
                        if (children.size() > 0) {
                            temp = children.get(children.size() - 1);
                            if (temp.getName().equals("recv")) {
                                temp.setAttribute("auth", "true");
                            } else {
                                children.get(children.size() - 2).setAttribute("auth", "true");
                            }
                        }
                    }
                    root.addContent(new Element("send").setContent(new CDATA(s)));
                }

            }
        }

        doc = new Document(root);
        doc.setDocType(docType1);
        sw = new StringWriter();
        out.output(doc, sw);
        xml = new String(sw.getBuffer());
        xml = xml.replace("<![CDATA[", "\n<![CDATA[");
        xml = xml.replace("]]>", "]]>\n");
        fw = new FileWriter("temp_uac.xml");
        fw.write(xml);
        fw.close();

        //--------------------- UAS ---------------------------------//
        root = new Element("scenario");
        root.setAttribute("name", scenarioName);

        for (SequentialMap<String, String> sm : packets) {
            if (!sm.isEmpty()) {
                String head = sm.firstKey();
                StringTokenizer st = new StringTokenizer(head, " ");
                String method = st.nextToken();
                if (!method.startsWith("SIP")) {
                    method = st.nextToken();
                    root.addContent(new Element("recv").setAttribute("request", new StringTokenizer(head, " ").nextToken()));

                } else {
                    String s = replaceText(sm, 1);
                    if (s.contains("authentication")) {
                        List<Element> children = root.getChildren();
                        children.get(children.size() - 1).setAttribute("auth", "true");
                    }
                    root.addContent(new Element("send").setContent(new CDATA(s)));
                }
            }
        }

        doc.removeContent();
        doc.setRootElement(root);
        doc.setDocType(docType2);
        sw = new StringWriter();
        out.output(doc, sw);
        xml = new String(sw.getBuffer());
        xml = xml.replace("<![CDATA[", "\n<![CDATA[");
        fw = new FileWriter("temp_uas.xml");
        fw.write(xml);
        fw.close();
    }

    public static Hashtable<String, CallSelection> getCalls(String pcap) {
        Packet p;
        Hashtable<String, CallSelection> callIDs = new Hashtable<String, CallSelection>();
        PcapHandle captor = null;

        try {
            captor = Pcaps.openOffline(pcap);
            p = captor.getNextPacket();
            while (p != null) {
                UdpPacket p1 = p.get(UdpPacket.class);
                if (p1 != null) {
                    UdpHeader h = p1.getHeader();
                    if (h.getSrcPort().valueAsInt() == 5060 || h.getDstPort().valueAsInt() == 5060) {
                        String data = new String(p1.getPayload().getRawData());

                        if (p1.getHeader() != null) {
                            System.out.println("Header: " + p1.getHeader());
                            System.out.println("Payload: " + data);
                        }
                        if (!data.isEmpty() && data.contains("Call-ID")) {
                            String callID = getCallID(data);
                            if (!callIDs.containsKey(callID)) {
                                callIDs.put(callID, getCallSelectionData(data));
                            }
                        }
                    }
                }
                p = captor.getNextPacket();
            }
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }
        captor.close();

        return callIDs;
    }

    private static String getCallID(String data) {
        int index = data.indexOf("Call-ID");

        if (index == -1) {
            return "-1";
        }
        String temp = data.substring(index, data.length());

        StringTokenizer st = new StringTokenizer(temp, ":\n");
        st.nextToken();
        return st.nextToken();
    }

    private static String getTag(String data) {
        int index = data.indexOf(";tag=");

        if (index == -1) {
            return "-1";
        }
        String temp = data.substring(index, data.length());

        StringTokenizer st = new StringTokenizer(temp, "=\n");
        st.nextToken();
        return st.nextToken();
    }

    private static CallSelection getCallSelectionData(String data) {
        int index;
        String requestLine, sourceIP, destIP, temp;
        StringTokenizer st;

        st = new StringTokenizer(data, "\n");
        requestLine = st.nextToken();

        index = data.indexOf("From");
        temp = data.substring(index, data.length());
        st = new StringTokenizer(temp, "@>");
        st.nextToken();
        sourceIP = st.nextToken();

        index = data.indexOf("To");
        temp = data.substring(index, data.length());
        st = new StringTokenizer(temp, "@>");
        st.nextToken();
        destIP = st.nextToken();

        return new CallSelection(requestLine, sourceIP, destIP);
    }

    private static String replaceText(SequentialMap<String, String> sm, int k) {
        StringBuffer erg = new StringBuffer("\n");
        erg.append(formatMethod(sm.firstKey(), rrs, k));

        boolean sdp = false;

        for (int i = 1; i < sm.size(); i++) {
            String key = sm.keyList().get(i);
            if (key.toCharArray().length == 1 && !sdp) {
                erg.append("\n");
                sdp = true;
            }
            if (key.equalsIgnoreCase("Via")) {
                if (cbViaL[k].isSelected()) {
                    erg.append(formatVia("[last_Via:]", k));//+getLastString(sm.get(key)),firstVia,k));
                } else {
                    erg.append(key + formatVia(sm.get(key), k));
                }
            } else if (key.equalsIgnoreCase("From")) {
                if (cbFromL[k].isSelected()) {
                    erg.append(formatFrom("[last_From:]" + getLastString(sm.get(key)), k));
                } else {
                    erg.append(key + formatFrom(sm.get(key), k));
                }
            } else if (key.equalsIgnoreCase("To")) {
                if (cbToL[k].isSelected()) {
                    erg.append(formatTo("[last_To:]" + getLastString(sm.get(key)), k));
                } else {
                    erg.append(key + formatTo(sm.get(key), k));
                }
            } else if (key.equalsIgnoreCase("Call-ID")) {
                if (cbCallIDL[k].isSelected()) {
                    erg.append("[last_Call-ID:]" + getLastString(sm.get(key)) + "\n");
                } else {
                    erg.append("Call-ID: [call_id]\n");
                }
            } else if (key.equalsIgnoreCase("CSeq")) {
                if (cbCseqL[k].isSelected()) {
                    erg.append("[last_CSeq:]" + getLastString(sm.get(key)) + "\n");
                } else {
                    erg.append(key + replaceCseq(sm.get(key)));
                }
            } else if (key.equalsIgnoreCase("Contact")) {
                erg.append(key + formatContact(sm.get(key), k));
            } else if (key.equalsIgnoreCase("Content-Length")) {
                erg.append(key + replaceContentLength(sm.get(key))).append("\n");
            } else if (key.equalsIgnoreCase("Authorization") || key.equalsIgnoreCase("Proxy-Authorization")) {
                erg.append(replaceAuthorization(sm.get(key)));
            } else if (key.equalsIgnoreCase("o")) {
                erg.append(key + formatO(sm.get(i), k));
            } else if (key.equalsIgnoreCase("m")) {
                erg.append(key + formatM(sm.get(i), k));
            } else if (key.equalsIgnoreCase("c")) {
                erg.append(key + formatC(sm.get(i), k));
            } else {
                erg.append(key).append(sm.get(i)).append("\n");
            }
        }
        return new String(erg.append("\n"));
    }

    public static String replaceIP(String value, String ipType) {
        return value.replaceFirst("(^.+[@ :])(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(\\D.*$)", "$1[" + ipType + "_ip]$3").replaceFirst("(^.+[@ ])([a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3})(\\W.*$)", "$1[" + ipType + "_ip]$3").replaceFirst("(^.+[@ :])(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$)", "$1[" + ipType + "_ip]").replaceFirst("(^.+[@ ])([a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}$)", "$1[" + ipType + "_ip]");
    }

    public static String replacePeerTagParam(String value) {
        return value.replaceFirst("(^.*)tag=.*$", "$1[peer_tag_param]");
    }

    public static String replacePort(String value, String portType) {
        return value.replaceFirst("(^.+:)(\\d{2,5})([ ;>].*$)", "$1[" + portType + "_port]$3").replaceFirst("(^.+:)(\\d{2,5}$)", "$1[" + portType + "_port]");
    }

    public static String replaceService(String value) {
        return value.replaceFirst("(^.+:)(\\w*)(@.+$)", "$1[service]$3");
    }

    public static String replaceTransport(String value) {
        return value.replaceFirst("(^.+/[0-9\\.]+/)(\\w+)( .+$)", "$1[transport]$3");
    }

    public static String replaceIPType(String value, String ipType) {
        return value.replaceFirst("(^.+ IP)(\\d)( .+$)", "$1[" + ipType + "_ip_type]$3");
    }

    public static String replaceTag(String value, String tag, String replace) {
        return value.replaceFirst("(^.+" + tag + "=)([^;>]*)([;>]?.*$)", "$1[" + replace + "]$3");
    }

    public static String replaceAuthorization(String value) {
        return value.replaceFirst("(^: )(.*$)", "[authentication username=\"abc\" password=\"abc\"]");
    }

    public static String replaceCseq(String value) {
        return value.replaceFirst("(^\\D+)(\\d{1,})(\\D+$)", "$1[cseq]$3");
    }

    private static String formatMethod(String value, boolean next_url, int k) {
        if (next_url) {
            System.out.println("START " + value);
            value = value.replaceFirst("(^\\w+ )[^ ]*( .*$)", "$1[next_url]$2");
            System.out.println(value + " " + next_url);
            rrs = false;
        } else {
            value = (cbServiceMethod[k].isSelected() ? replaceService(value) : value);
            value = (cbIPMethod[k].isSelected() ? replaceIP(value, "remote") : value);
            value = (cbPortMethod[k].isSelected() ? replacePort(value, "remote") : value);
        }

        return value + "\n";
    }

    private static String formatVia(String value, int k) {
        value = (cbTransportVia[k].isSelected() ? replaceTransport(value) : value);
        value = (cbIPVia[k].isSelected() ? replaceIP(value, "local") : value);
        value = (cbPortVia[k].isSelected() ? replacePort(value, "local") : value);
        value = (cbBranch[k].isSelected() ? replaceTag(value, "branch", "branch") : value);

        return value + "\n";
    }

    public static String formatFrom(String value, int k) {
        value = (cbIPFrom[k].isSelected() ? replaceIP(value, "local") : value);
        value = (cbPortFrom[k].isSelected() ? replacePort(value, "local") : value);
        value = (cbCallNumberFrom[k].isSelected() ? replaceTag(value, "tag", "call_number") : value);

        return value + "\n";
    }

    public static String formatTo(String value, int k) {
        value = (cbServiceTo[k].isSelected() ? replaceService(value) : value);
        value = (cbIPTo[k].isSelected() ? replaceIP(value, "remote") : value);
        value = (cbPortTo[k].isSelected() ? replacePort(value, "remote") : value);
        value = (cbCallNumberTo[k].isSelected() ? replacePeerTagParam(value) : value);

        return value + "\n";
    }

    private static String formatO(String value, int k) {
        value = (cbIPTypeOrigin[k].isSelected() ? replaceIPType(value, "local") : value);
        value = (cbIPOrigin[k].isSelected() ? replaceIP(value, "local") : value);

        return value + "\n";
    }

    private static String formatC(String value, int k) {
        value = (cbIPTypeConnData[k].isSelected() ? replaceIPType(value, "media") : value);
        value = (cbIPConnData[k].isSelected() ? replaceIP(value, "media") : value);

        return value + "\n";
    }

    private static String formatM(String value, int k) {
        return (cbPortMediDescription[k].isSelected() ? value.replaceFirst("(^\\D+ )(\\d{2,5})( .+$)", "$1[media_port]$3") : value);
    }

    private static String formatContact(String value, int k) {
        value = (cbIPContact[k].isSelected() ? replaceIP(value, "local") : value);
        value = (cbPortContact[k].isSelected() ? replacePort(value, "local") : value);
        value = (cbTransportContact[k].isSelected() ? replaceTag(value, "transport", "transport") : value);

        return value + "\n";
    }

    private static String replaceContentLength(String value) {
        return (Integer.parseInt(value.substring(1, value.length() - 1).trim()) > 0 ? ": [len]" : value);
    }

    private static String getLastString(String value) {
        return (value.split(";").length != 1 ? value.replaceFirst("(^.*)(;.*$)", "$2") : "");
    }
}
