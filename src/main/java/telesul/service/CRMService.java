/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

import telesul.model.ValidationResult;

/**
 *
 * @author lmohan
 */
public interface CRMService {

    public ValidationResult wsConsultarPorTelefone(String telephone);

    public ValidationResult wsConsultarPorCPFCNPJCodigoCliente(String cpf_cnpj);

//    public int wsConsultarPorCodigoCliente(String codigoCliente);
}
