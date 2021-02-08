package com.jueee.service;

import com.jueee.bean.Love;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.types.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Test
    public void test(){
        List<Love> pathList = sessionService.test();
        pathList.forEach(t->log.info(t.toString()));
    }
    @Test
    public void getNodeCount(){
        Long resultNum = sessionService.getNodeCount();
        log.info("[resultNum]"+resultNum);
    }
    @Test
    public void getRelationshipCount(){
        Long resultNum = sessionService.getRelationshipCount();
        log.info("[resultNum]"+resultNum);
    }
}