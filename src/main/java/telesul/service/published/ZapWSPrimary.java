/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service.published;

import javax.jws.WebParam;
import javax.jws.WebService;
import telesul.model.ValidationResult;

/**
 *
 * @author lmohan
 */
@WebService
public interface ZapWSPrimary {

//    @WebResult(name = "retval")
//    public boolean isFeriado();
//
//    @WebResult(name = "retval")
//    public boolean isExceptionDiaWithinHours();

    public void wsStatisticsTransfered(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName);

    public void wsStatisticsAbandoned(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "enteredInPesquisa") int enteredInPesquisa);

    public void wsStatisticsTerminated(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "terminatedReason") int terminatedReason);

    public void wsStatisticsPesquisa(@WebParam(name = "telephone") String telephone,
            @WebParam(name = "digitsTyped") String digitsTyped,
            @WebParam(name = "interactionPoints") String interactionPoints,
            @WebParam(name = "invalidOptionCount") int invalidOptionCount,
            @WebParam(name = "transferedDirectToDefaultSkill") int transferedDirectToDefaultSkill,
            @WebParam(name = "skillset") String skillset,
            @WebParam(name = "flowName") String flowName,
            @WebParam(name = "answers") String answers, @WebParam(name = "agent") String agent);

    public ValidationResult wsConsultarPorTelefone(@WebParam(name = "telephone") String telephone);

    public ValidationResult wsConsultarPorCPFCNPJCodigoCliente(@WebParam(name = "cpf_cnpj") String cpf_cnpj);
    
//    public int wsConsultarPorCodigoCliente(@WebParam(name = "codigoCliente") String codigoCliente);
    
}
