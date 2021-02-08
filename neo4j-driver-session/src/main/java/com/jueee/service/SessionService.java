package com.jueee.service;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SessionService {

    @Autowired
    private Session session;

    public List<Path> randomPerson(){
        String searchSQL = "match p=(n:Person)-[t]->(m) where 1=1 return p limit 20";
        log.info("[searchSQL]"+searchSQL);
        long start = System.currentTimeMillis();
        List<Path> pathList = new ArrayList<>();
        Result result = session.run(searchSQL);
        while ( result.hasNext() ) {
            Record record = result.next();
            Value value = record.get(0);
            pathList.add(value.asPath());
        }
        log.info("[UseTime]"+(System.currentTimeMillis()-start)+"ms"+"[Size]"+pathList.size());
        return pathList;
    }

    public Long getNodeCount() {
        Long resultNum = 0L;
        try {
            String searchSQL = "match (n:Person) return count(n)";
            Result result = session.run(searchSQL);
            while ( result.hasNext() ) {
                Record record = result.next();
                Value value = record.get(0);
                resultNum = value.asLong();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return resultNum;
    }

    public Long getRelationshipCount() {
        Long resultNum = 0L;
        try {
            String searchSQL = "match p=(n:Person)-[]->(m:Person) return count(n)";
            Result result = session.run(searchSQL);
            while ( result.hasNext() ) {
                Record record = result.next();
                Value value = record.get(0);
                resultNum = value.asLong();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return resultNum;
    }
}
