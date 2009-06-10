/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package converter.res;

/**
 *
 * @author christian
 */
public class CallSelection {
    
    private String requestLine;
    private String sourceIP;
    private String destIP;
    
    public CallSelection(String requestLine, String sourceIP, String destIP) {
        this.requestLine = requestLine;
        this.sourceIP = sourceIP;
        this.destIP = destIP;
    }
    
    public String getRequestLine() {
        return requestLine;
    }
    
    public String getSourceIP() {
        return sourceIP;
    }
    
    public String getDestIP() {
        return destIP;
    }
    
    public String toString() {
        return requestLine+"\nSource IP: "+sourceIP+"\nDestination IP: "+destIP;
    }
}
