/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

/**
 *
 * @author lmohan
 */
public class ValidationResult {

    private String guid;
    private int ofertas;
    private String typeOfValidation;

    public int getOfertas() {
        return ofertas;
    }

    public void setOfertas(int ofertas) {
        this.ofertas = ofertas;
    }

    public String getTypeOfValidation() {
        return typeOfValidation;
    }

    public void setTypeOfValidation(String typeOfValidation) {
        this.typeOfValidation = typeOfValidation;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
