/**
 * IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */
package telesul.crm;

public class IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage
    extends java.lang.Exception {
    private static final long serialVersionUID = 1465484838357L;
    private telesul.crm.DiscoveryServiceStub.DiscoveryServiceFaultE faultMessage;

    public IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage() {
        super(
            "IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage");
    }

    public IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage(
        java.lang.String s) {
        super(s);
    }

    public IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage(
        java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public IDiscoveryService_Execute_DiscoveryServiceFaultFault_FaultMessage(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        telesul.crm.DiscoveryServiceStub.DiscoveryServiceFaultE msg) {
        faultMessage = msg;
    }

    public telesul.crm.DiscoveryServiceStub.DiscoveryServiceFaultE getFaultMessage() {
        return faultMessage;
    }
}
