/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

import telesul.model.IvrState;

/**
 *
 * @author lmohan
 */
public interface ZapService {

    public boolean isFeriado();

    public boolean isExceptionDiaWithinHours();

    public void saveStatisticsTransfered(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName);

    public void saveStatisticsAbandoned(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, String skillset, String flowName, int enteredInPesquisa);

    public void saveStatisticsTerminated(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, int terminatedReason);

    public void saveStatisticsPesquisa(String telephone, String digitsTyped, String interactionPoints, int invalidOptionCount, int transferedDirectToDefaultSkill, String skillset, String flowName, String answers,String agent);

    public int getStateCode(String telefone_origem);

    public IvrState getStateByPrefixo(int prefixo);
}
