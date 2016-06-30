/**
 * IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.3  Built on : May 30, 2016 (04:08:57 BST)
 */
package telesul.crm;

public class IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage
    extends java.lang.Exception {
    private static final long serialVersionUID = 1465484889447L;
    private telesul.crm.OrganizationServiceStub.OrganizationServiceFaultE faultMessage;

    public IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage() {
        super(
            "IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage");
    }

    public IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage(
        java.lang.String s) {
        super(s);
    }

    public IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage(
        java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public IOrganizationService_Execute_OrganizationServiceFaultFault_FaultMessage(
        java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(
        telesul.crm.OrganizationServiceStub.OrganizationServiceFaultE msg) {
        faultMessage = msg;
    }

    public telesul.crm.OrganizationServiceStub.OrganizationServiceFaultE getFaultMessage() {
        return faultMessage;
    }
}
