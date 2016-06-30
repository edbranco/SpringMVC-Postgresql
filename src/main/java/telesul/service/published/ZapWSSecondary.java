/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service.published;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author lmohan
 */
@WebService
public interface ZapWSSecondary {

    @WebResult(name = "retval")
    public boolean sisFeriado();

    @WebResult(name = "retval")
    public boolean sisExceptionDiaWithinHours();

    public void swsStatisticsTransfered(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName);

    public void swsStatisticsAbandoned(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "enteredInPesquisa") int enteredInPesquisa);

    public void swsStatisticsTerminated(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "terminatedReason") int terminatedReason);

    public void swsStatisticsPesquisa(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "answers") String answers, @WebParam(name = "agent") String agent);

    public int swsConsultarPorTelefone(@WebParam(name = "telephone") String telephone);

    public int swsConsultarPorCPFCNPJ(@WebParam(name = "cpf_cnpj") String cpf_cnpj);
}
