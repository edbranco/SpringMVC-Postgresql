/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telesul.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author lmohan
 */
@Entity
public class Feriado implements Serializable{
    
    @Id
    @GeneratedValue
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date feriado;
    private String feriado_description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFeriado() {
        return feriado;
    }

    public void setFeriado(Date feriado) {
        this.feriado = feriado;
    }

    public String getFeriado_description() {
        return feriado_description;
    }

    public void setFeriado_description(String feriado_description) {
        this.feriado_description = feriado_description;
    }
    
}
