/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service.published;

import javax.jws.WebMethod;
import javax.jws.WebService;
import telesul.service.ZapService;

/**
 *
 * @author lmohan
 */
@WebService()
public class ZapWSPrimaryImpl implements ZapWSPrimary {

    private final ZapService zapService;

    public ZapWSPrimaryImpl(ZapService zapService) {
        this.zapService = zapService;
    }

    @Override
    @WebMethod()
    public boolean isFeriado() {
        return zapService.isFeriado();
    }

    @Override
    @WebMethod()
    public boolean isExceptionDiaWithinHours() {
        return zapService.isExceptionDiaWithinHours();
    }

    @Override
    @WebMethod()
    public void wsStatisticsTransfered(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName) {
        zapService.saveStatisticsTransfered(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName);
    }

    @Override
    @WebMethod()
    public void wsStatisticsAbandoned(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName,int enteredInPesquisa) {
        zapService.saveStatisticsAbandoned(telephone, digitsTyped, interactionPoints, invalidOptionCount, skillset, flowName,enteredInPesquisa);
    }

    @Override
    @WebMethod()
    public void wsStatisticsTerminated(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, int terminatedReason) {
        zapService.saveStatisticsTerminated(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName, terminatedReason);
    }

    @Override
    @WebMethod()
    public void wsStatisticsPesquisa(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, String answers,String agent) {
        zapService.saveStatisticsPesquisa(telephone, digitsTyped, interactionPoints, invalidOptionCount, transferedDirectToDefaultSkill, skillset, flowName, answers,agent);
    }
}