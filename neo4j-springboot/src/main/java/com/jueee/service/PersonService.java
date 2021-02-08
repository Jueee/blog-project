package com.jueee.service;

import com.jueee.bean.Love;
import com.jueee.bean.Person;
import com.jueee.dao.LoveRepository;
import com.jueee.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LoveRepository loveRepository;

    public Person addPerson(Person person){
        return personRepository.save(person);
    }

    public Person findOnePerson(long id) {
        return personRepository.findById(id).get();
    }

    public Love loves(Love love) {
        return loveRepository.save(love);
    }

    public Iterable<Love> findAll() {
        return loveRepository.findAll();
    }
}


