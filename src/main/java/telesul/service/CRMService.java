/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.service;

/**
 *
 * @author lmohan
 */
public interface CRMService {

    public int wsConsultarPorTelefone(String telephone);

    public int wsConsultarPorCPFCNPJ(String cpf_cnpj);
}
