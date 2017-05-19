/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.postgresql.controller;

import br.postgresql.model.Person;
import br.postgresql.repository.PersonRepository;
import br.postgresql.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ebranco
 */
@Controller
public class CrudController {
    @Autowired
    CrudService crudService;
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Person person){
        
        System.out.println("Teste"+person.getName());
        crudService.save(person);
        return new ModelAndView("index");
    }
}
