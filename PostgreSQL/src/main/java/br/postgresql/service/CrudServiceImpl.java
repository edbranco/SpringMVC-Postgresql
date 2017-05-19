/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.postgresql.service;

import br.postgresql.model.Person;
import br.postgresql.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ebranco
 */
@Service
public class CrudServiceImpl implements CrudService {

//    @Autowired
//    PersonRepository personRep;

    @Override
    @Transactional
    public Person save(Person person) {
       // return personRep.save(person);
       return null;
    }
}
