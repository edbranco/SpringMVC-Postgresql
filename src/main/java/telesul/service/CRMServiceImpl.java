/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.rmi.RemoteException;
import java.util.UUID;

import telesul.crm.IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage;
import telesul.crm.OrganizationServiceStub;
import telesul.crm.OrganizationServiceStub.ConditionExpression;
import telesul.crm.OrganizationServiceStub.ConditionOperator;
import telesul.crm.OrganizationServiceStub.Entity;
import telesul.crm.OrganizationServiceStub.EntityCollection;
import telesul.crm.OrganizationServiceStub.FilterExpression;
import telesul.crm.OrganizationServiceStub.QueryExpression;
import telesul.crm.helper.DeviceRegistrationFailedException;
import telesul.crm.helper.OnlineAuthenticationPolicy;
import telesul.crm.helper.RequestDateTimeData;
import telesul.crm.helper.SecurityData;
import telesul.crm.helper.WsdlTokenManager;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.wsdl.WSDLException;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.stream.XMLStreamException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import telesul.model.ValidationResult;

/**
 *
 * @author lmohan
 */
@Service
public class CRMServiceImpl implements CRMService {

    private final Logger logger = LoggerFactory.getLogger(CRMServiceImpl.class);
    @Autowired
    ServletContext servletContext;
    static String appPath;
    /**
     * Microsoft account (e.g. youremail@live.com) or Microsoft Office 365 (Org
     * ID e.g. youremail@yourorg.onmicrosoft.com) User Name.
     */
    private static final String USER_NAME = "crm_atendimento@zapcorp.com.br";
    /**
     * Microsoft account or Microsoft Office 365 (Org ID) Password.
     */
    private static final String USER_PASSWORD = "Dozu2509";
    /**
     * Unique Name of the organization
     */
//    private static final String OrganizationUniqueName = "orge7a60768";
    /**
     * URL for the Discovery Service For North America Microsoft account,
     * discovery service url is
     * https://dev.crm.dynamics.com/XRMServices/2011/Discovery.svc Microsoft
     * office 365, discovery service url is
     * https://disco.crm.dynamics.com/XRMServices/2011/Discovery.svc To use
     * appropriate discovery service url for other environments refer
     * http://technet.microsoft.com/en-us/library/gg309401.aspx
     */
//    private static final String DISCOVERY_SERVICE_URL = "https://disco.crm.dynamics.com/XRMServices/2011/Discovery.svc";
    private static final String ORG_URL = "https://zapcrmdesenvolvimento.api.crm2.dynamics.com/XRMServices/2011/Organization.svc";
    /**
     * Suffix for the Flat WSDL
     */
    private static final String WSDL_SUFFIX = "?wsdl";
    private OrganizationServiceStub serviceStub;

    private final int ATTRIBUTE_FOUND = 0;
    private final int ATTRIBUTE_NOT_FOUND = -1;
    private final String telephoneAttr = "telephone1";
    private final String codigoClienteAttr = "zap_codigocliente";
    private final String cpfAttr = "zap_cpf";
    private final String cnpjAttr = "zap_cnpj";
    private final String ofertaAttr = "zap_qtdofertascontratadas";
    private final String accountidAttr = "accountid";

    public CRMServiceImpl() {
    }

    @PostConstruct
    public synchronized void initialize() {
        try {
            // The discovery service stub cannot be reused against the organization service
            // as the Issuer and AppliesTo may differ between the discovery and organization services.
            // Retrieve the authentication policy for the organization service.
            OnlineAuthenticationPolicy organizationPolicy
                    = new OnlineAuthenticationPolicy(ORG_URL + WSDL_SUFFIX);
            WsdlTokenManager orgTokenManager = new WsdlTokenManager();
            // Authenticate the user using the organization authentication policy.
            SecurityData securityData = orgTokenManager.authenticate(ORG_URL,
                    USER_NAME,
                    USER_PASSWORD,
                    organizationPolicy.getAppliesTo(),
                    organizationPolicy.getPolicy(),
                    organizationPolicy.getIssuerUri());
            // Retrieve organization stub using organization URL with the security data.
            serviceStub = createOrganizationServiceStub(
                    ORG_URL,
                    securityData);
            logger.info("CRM connection is initialized successfully!");

        } catch (ParserConfigurationException | SAXException | IOException | URISyntaxException | XPathExpressionException | WSDLException | IllegalStateException | DeviceRegistrationFailedException | XMLStreamException ex) {
            logger.error("CRM connection is failed to initialize successfully!", ex);
        }
    }

    @Override
    public ValidationResult wsConsultarPorTelefone(String telephone) {
        ValidationResult ret = new ValidationResult();
        ret.setOfertas(ATTRIBUTE_NOT_FOUND);
        ret.setTypeOfValidation("202");//Telefone Nâo Encontrado
        try {
            Map<String, String> result = find(telephoneAttr, telephone, telephoneAttr, ofertaAttr, accountidAttr);

            if (StringUtils.isNotBlank(result.get(telephoneAttr))) {
                if (StringUtils.isNotBlank(result.get(accountidAttr))) {
                    ret.setGuid(result.get(accountidAttr));
                }
                if (StringUtils.isNotBlank(result.get(ofertaAttr))) {
                    logger.info("wsConsultarPorTelefone " + ofertaAttr + "::" + result.get(ofertaAttr));
                    int offertas = Integer.parseInt(result.get(ofertaAttr));
                    ret.setOfertas(offertas);
                    ret.setTypeOfValidation("201");//Telefone Encontrado
                    return ret;
                }

                logger.info("wsConsultarPorTelefone " + telephoneAttr + "::" + result.get(telephoneAttr));
                ret.setTypeOfValidation("201");//CodigoCliente Encontrado
                ret.setOfertas(ATTRIBUTE_FOUND);
            }
        } catch (RemoteException | IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage ex) {
            logger.error("wsConsultarPorTelefone error " + ex, ex);
            initialize();
            return wsConsultarPorTelefone(telephone);
        }
        return ret;
    }

    @Override
    public ValidationResult wsConsultarPorCPFCNPJCodigoCliente(String cpf_cnpj) {
        try {
            switch (cpf_cnpj.length()) {
                case 11: {
                    return consultaCPF(cpf_cnpj);
                }
                case 14: {
                    return consultaCNPJ(cpf_cnpj);
                }
                default:
                    return consultarPorCodigoCliente(cpf_cnpj);
            }
        } catch (RemoteException | IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage ex) {
            logger.error("wsConsultarPorCPFCNPJCodigoCliente error " + ex, ex);
            initialize();
            return wsConsultarPorCPFCNPJCodigoCliente(cpf_cnpj);
        }
    }

    public ValidationResult consultarPorCodigoCliente(String codigoCliente) throws RemoteException, IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage {
        ValidationResult ret = new ValidationResult();
        ret.setOfertas(ATTRIBUTE_NOT_FOUND);
        ret.setTypeOfValidation("209");//CodigoCliente Nâo Encontrado

        Map<String, String> result = find(codigoClienteAttr, codigoCliente, codigoClienteAttr, ofertaAttr, accountidAttr);

        if (StringUtils.isNotBlank(result.get(codigoClienteAttr))) {
            if (StringUtils.isNotBlank(result.get(accountidAttr))) {
                ret.setGuid(result.get(accountidAttr));
            }
            if (StringUtils.isNotBlank(result.get(ofertaAttr))) {
                logger.info("consultarPorCodigoCliente " + ofertaAttr + "::" + result.get(ofertaAttr));
                int offertas = Integer.parseInt(result.get(ofertaAttr));
                ret.setOfertas(offertas);
                ret.setTypeOfValidation("206");//CodigoCliente Encontrado
                return ret;
            }
            logger.info("consultarPorCodigoCliente " + codigoClienteAttr + "::" + result.get(codigoClienteAttr));
            ret.setTypeOfValidation("206");//CodigoCliente Encontrado
            ret.setOfertas(ATTRIBUTE_FOUND);
            return ret;
        }

        return ret;
    }

    public ValidationResult consultaCPF(String cpfVal) throws RemoteException, IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage {
        ValidationResult ret = new ValidationResult();
        ret.setOfertas(ATTRIBUTE_NOT_FOUND);
        ret.setTypeOfValidation("207");//CPF Nâo Encontrado

        String cpf = buildCPF(cpfVal);
        logger.info("consultaCPF finding for CPF::{}", cpf);
        Map<String, String> result = find(cpfAttr, cpf, cpfAttr, ofertaAttr, accountidAttr);
        if (StringUtils.isNotBlank(result.get(cpfAttr))) {
            if (StringUtils.isNotBlank(result.get(accountidAttr))) {
                ret.setGuid(result.get(accountidAttr));
            }
            if (StringUtils.isNotBlank(result.get(ofertaAttr))) {
                logger.info("consultaCPF " + ofertaAttr + "::" + result.get(ofertaAttr));
                int offertas = Integer.parseInt(result.get(ofertaAttr));
                ret.setOfertas(offertas);
                ret.setTypeOfValidation("204");//CPF Encontrado
                return ret;
            }
            logger.info("consultaCPF " + cpfAttr + "::" + result.get(cpfAttr));
            ret.setTypeOfValidation("204");//CPF Encontrado
            ret.setOfertas(ATTRIBUTE_FOUND);
            return ret;
        }
        return ret;
    }

    public ValidationResult consultaCNPJ(String cnpjVal) throws RemoteException, IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage {
        ValidationResult ret = new ValidationResult();
        ret.setOfertas(ATTRIBUTE_NOT_FOUND);
        ret.setTypeOfValidation("208");//CNPJ Nâo Encontrado

        String cnpj = buildCNPJ(cnpjVal);
        logger.info("consultaCNPJ finding for CNPJ::{}", cnpj);
        Map<String, String> result = find(cnpjAttr, cnpj, cnpjAttr, ofertaAttr, accountidAttr);
        if (StringUtils.isNotBlank(result.get(cnpjAttr))) {
            if (StringUtils.isNotBlank(result.get(accountidAttr))) {
                ret.setGuid(result.get(accountidAttr));
            }
            if (StringUtils.isNotBlank(result.get(ofertaAttr))) {
                logger.info("consultaCNPJ " + ofertaAttr + "::" + result.get(ofertaAttr));
                int offertas = Integer.parseInt(result.get(ofertaAttr));
                ret.setOfertas(offertas);
                ret.setTypeOfValidation("205");//CNPJ Encontrado
                return ret;
            }
            logger.info("consultaCNPJ" + cnpjAttr + "::" + result.get(cnpjAttr));
            ret.setTypeOfValidation("205");
            ret.setOfertas(ATTRIBUTE_FOUND);
            return ret;
        }
        return ret;
    }

    public String buildCPF(String ivrCPF) {
        //format 069.867.578-91
        StringBuilder cpf = new StringBuilder();
        if (ivrCPF.length() == 11) {
            for (int i = 0; i < ivrCPF.length(); i++) {
                switch (i) {
                    case 3:
                    case 6:
                        cpf.append(".");
                        break;
                    case 9:
                        cpf.append("-");
                        break;
                }
                cpf.append(ivrCPF.charAt(i));

            }
        } else {
            cpf.append(ivrCPF);
        }
        return cpf.toString();
    }

    public String buildCNPJ(String ivrCNPJ) {
        //format 62.307.210/0002-65
        StringBuilder cnpj = new StringBuilder();
        if (ivrCNPJ.length() == 14) {
            for (int i = 0; i < ivrCNPJ.length(); i++) {
                switch (i) {
                    case 2:
                    case 5:
                        cnpj.append(".");
                        break;
                    case 8:
                        cnpj.append("/");
                        break;
                    case 12:
                        cnpj.append("-");
                        break;
                    default:
                        break;
                }
                cnpj.append(ivrCNPJ.charAt(i));
            }
        } else {
            cnpj.append(ivrCNPJ);
        }
        return cnpj.toString();
    }

    public Map<String, String> find(String inputAttrName, String inputAttrValue, String... resultAttrName) throws RemoteException, IOrganizationService_RetrieveMultiple_OrganizationServiceFaultFault_FaultMessage {
        Map<String, String> map = new HashMap<>();
        //  Query using ConditionExpression  
        ConditionExpression cond = new ConditionExpression();
        cond.setAttributeName(inputAttrName);
        cond.setOperator(ConditionOperator.Equal);
        OrganizationServiceStub.ArrayOfanyType vals = new OrganizationServiceStub.ArrayOfanyType();
        vals.addAnyType(inputAttrValue);
        cond.setValues(vals);

        OrganizationServiceStub.ArrayOfConditionExpression conds = new OrganizationServiceStub.ArrayOfConditionExpression();
        FilterExpression filter = new FilterExpression();
        conds.addConditionExpression(cond);
        filter.setConditions(conds);

        QueryExpression qe = new QueryExpression();
        qe.setEntityName("account");

        OrganizationServiceStub.ColumnSet colSet = new OrganizationServiceStub.ColumnSet();

        OrganizationServiceStub.ArrayOfstring cols = new OrganizationServiceStub.ArrayOfstring();
        cols.setString(resultAttrName);
        colSet.setColumns(cols);

        qe.setColumnSet(colSet);

        OrganizationServiceStub.ArrayOfFilterExpression arrayOfFilterExpression = new OrganizationServiceStub.ArrayOfFilterExpression();
        arrayOfFilterExpression.addFilterExpression(filter);
        FilterExpression expression = new FilterExpression();
        expression.setFilters(arrayOfFilterExpression);
        qe.setCriteria(expression);

        OrganizationServiceStub.RetrieveMultiple readMultiple = new OrganizationServiceStub.RetrieveMultiple();
        readMultiple.setQuery(qe);

        OrganizationServiceStub.RetrieveMultipleResponse response = serviceStub.retrieveMultiple(readMultiple);
        EntityCollection resultCollection = response.getRetrieveMultipleResult();
        OrganizationServiceStub.ArrayOfEntity entities = resultCollection.getEntities();
        Entity[] ents = entities.getEntity();
        if (ents != null) {
            for (Entity result : ents) {
                OrganizationServiceStub.AttributeCollection attributes = result.getAttributes();
                OrganizationServiceStub.KeyValuePairOfstringanyType[] keyValuePairs = attributes.getKeyValuePairOfstringanyType();
                for (OrganizationServiceStub.KeyValuePairOfstringanyType keyValuePair : keyValuePairs) {
                    logger.info(keyValuePair.getKey() + ": " + keyValuePair.getValue());
                    String value = keyValuePair.getValue().toString();
                    if (StringUtils.isBlank(value) || StringUtils.equals(value, "null")) {
                        value = "";
                    }
                    map.put(keyValuePair.getKey(), value);
                }
            }
        }
        return map;
    }

    public OrganizationServiceStub getServiceStub() {
        return serviceStub;
    }

    private OrganizationServiceStub createOrganizationServiceStub(String organizationServiceURL, SecurityData securityData)
            throws RemoteException, XMLStreamException {
        try {
            OrganizationServiceStub stub = new OrganizationServiceStub(getConfigurationContext(), organizationServiceURL);
            setServiceClientOptions(stub._getServiceClient(), securityData);
            return stub;
        } catch (RemoteException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private void setServiceClientOptions(ServiceClient sc, SecurityData securityData)
            throws AxisFault, XMLStreamException {
        Options options = sc.getOptions();

        options.setMessageId("urn:uuid:" + UUID.randomUUID().toString());

        EndpointReference endPoint = new EndpointReference("http://www.w3.org/2005/08/addressing/anonymous");
        options.setReplyTo(endPoint);

        sc.setOptions(options);
        sc.addHeader(createCRMSecurityHeaderBlock(securityData));
        try {
            sc.engageModule("addressing");
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    private ConfigurationContext getConfigurationContext() throws AxisFault {
        System.setProperty("appPath", servletContext.getRealPath("/"));
        String workingDirectory = System.getProperty("appPath");

        String fileSeperator = System.getProperty("file.separator");
        String pathToAxis2File = workingDirectory + fileSeperator + "WEB-INF" + fileSeperator + "axis2.xml";

        logger.debug("Working directory: " + workingDirectory);
        logger.debug("Path to Axis2.xml file: " + pathToAxis2File);

        ConfigurationContext ctx;
        try {
            ctx = ConfigurationContextFactory.createConfigurationContextFromFileSystem(workingDirectory, pathToAxis2File);
        } catch (AxisFault e) {
            logger.error(e.getMessage());
            throw e;
        }
        return ctx;
    }

    private SOAPHeaderBlock createCRMSecurityHeaderBlock(SecurityData securityData)
            throws XMLStreamException {
        RequestDateTimeData dateTimeData = WsdlTokenManager.getRequestDateTime();

        String currentDateTime = dateTimeData.getCreatedDateTime();
        String expireDateTime = dateTimeData.getExpiresDateTime();

        String securityHeaderTemplate = "<EncryptedData "
                + "    xmlns=\"http://www.w3.org/2001/04/xmlenc#\""
                + "     Id=\"Assertion0\" "
                + "    Type=\"http://www.w3.org/2001/04/xmlenc#Element\">"
                + "    <EncryptionMethod "
                + "        Algorithm=\"http://www.w3.org/2001/04/xmlenc#tripledes-cbc\"/>"
                + "    <ds:KeyInfo "
                + "        xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\">"
                + "        <EncryptedKey>"
                + "            <EncryptionMethod "
                + "                Algorithm=\"http://www.w3.org/2001/04/xmlenc#rsa-oaep-mgf1p\"/>"
                + "            <ds:KeyInfo Id=\"keyinfo\">"
                + "                <wsse:SecurityTokenReference "
                + "                    xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
                + "                    <wsse:KeyIdentifier "
                + "                        EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\" "
                + "                        ValueType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-x509-token-profile-1.0#X509SubjectKeyIdentifier\">%s</wsse:KeyIdentifier>"
                + "                </wsse:SecurityTokenReference>"
                + "            </ds:KeyInfo>"
                + "            <CipherData>"
                + "                <CipherValue>%s</CipherValue>"
                + "            </CipherData>"
                + "        </EncryptedKey>"
                + "    </ds:KeyInfo>"
                + "    <CipherData>"
                + "        <CipherValue>%s</CipherValue>"
                + "    </CipherData>"
                + "</EncryptedData>";

        String securityHeader = String.format(
                securityHeaderTemplate,
                securityData.getKeyIdentifier(),
                securityData.getSecurityToken0(),
                securityData.getSecurityToken1()
        );

        try {

            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace securityNS = factory.createOMNamespace("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "o");
            OMNamespace utitlityNS = factory.createOMNamespace("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "u");

            OMElement timeStamp = factory.createOMElement("Timestamp", utitlityNS);
            timeStamp.addAttribute("Id", "_0", utitlityNS);

            OMElement created = factory.createOMElement("Created", utitlityNS);
            OMText createdTime = factory.createOMText(currentDateTime + "Z");
            created.addChild(createdTime);

            OMElement expires = factory.createOMElement("Expires", utitlityNS);
            OMText expiresTime = factory.createOMText(expireDateTime + "Z");
            expires.addChild(expiresTime);

            timeStamp.addChild(created);
            timeStamp.addChild(expires);

            SOAPHeaderBlock wsseHeader = OMAbstractFactory.getSOAP12Factory().createSOAPHeaderBlock("Security", securityNS);
            wsseHeader.setMustUnderstand(true);

            wsseHeader.addChild(timeStamp);
            wsseHeader.addChild(AXIOMUtil.stringToOM(factory, securityHeader));

            return wsseHeader;
        } catch (XMLStreamException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

}
