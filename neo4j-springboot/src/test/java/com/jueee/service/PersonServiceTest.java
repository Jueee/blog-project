package com.jueee.service;

import com.jueee.bean.Love;
import com.jueee.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Test
    public void test1(){
        Person test1 = new Person();
        test1.setName("test1");
        Person result1 = personService.addPerson(test1);
        log.info(result1.toString());

        Person test2 = new Person();
        test2.setName("test2");
        Person result2 = personService.addPerson(test2);
        log.info(result2.toString());

        Person person1 = personService.findOnePerson(result1.getId());
        Person person2 = personService.findOnePerson(result2.getId());
        Love love = new Love();
        love.setStartNode(person1);
        love.setEndNode(person2);
        Love result =  personService.loves(love);
        log.info(result.toString());
    }

    @Test
    public void test2(){
        Iterable<Love> loves = personService.findAll();
        for (Love love:loves){
            log.info(love.toString());
        }
    }
}