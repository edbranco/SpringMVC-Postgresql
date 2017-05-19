/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.postgresql.repository;

import br.postgresql.model.Person;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ebranco
 */
@Repository
public interface PersonRepository extends BaseRepository<Person, Long> {
    public Person findByName(String name);
}
