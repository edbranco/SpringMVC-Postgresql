/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service.published;

import javax.jws.WebMethod;
import javax.jws.WebService;
import telesul.service.CRMService;
import telesul.service.ZapService;

/**
 *
 * @author lmohan
 */
@WebService()
public class ZapWSSecondaryImpl implements ZapWSSecondary {

    private final ZapService zapService;
    private final CRMService crmService;

    public ZapWSSecondaryImpl(ZapService zapService, CRMService crmService) {
        this.zapService = zapService;
        this.crmService = crmService;
    }

    @Override
    @WebMethod()
    public boolean sisFeriado() {
        return zapService.isFeriado();
    }

    @Override
    @WebMethod()
    public boolean sisExceptionDiaWithinHours() {
        return zapService.isExceptionDiaWithinHours();
    }

    @Override
    @WebMethod()
    public void swsStatisticsTransfered(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName) {
        zapService.saveStatisticsTransfered(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName);
    }

    @Override
    @WebMethod()
    public void swsStatisticsAbandoned(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, int enteredInPesquisa) {
        zapService.saveStatisticsAbandoned(telephone, digitsTyped, interactionPoints, invalidOptionCount, skillset, flowName, enteredInPesquisa);
    }

    @Override
    @WebMethod()
    public void swsStatisticsTerminated(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, int terminatedReason) {
        zapService.saveStatisticsTerminated(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName, terminatedReason);
    }

    @Override
    @WebMethod()
    public void swsStatisticsPesquisa(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, String answers, String agent) {
        zapService.saveStatisticsPesquisa(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName, answers, agent);
    }

    @Override
    @WebMethod()
    public int swsConsultarPorTelefone(String telephone) {
        return crmService.wsConsultarPorTelefone(telephone);
    }

    @Override
    @WebMethod()
    public int swsConsultarPorCPFCNPJ(String cpf_cnpj) {
        return crmService.wsConsultarPorCPFCNPJ(cpf_cnpj);
    }
}
