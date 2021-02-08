package com.jueee.service;

import com.jueee.bean.Love;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SessionService {

    @Autowired
    private Session session;

    public List<Love> test(){
        String searchSQL = "match p=(n:Person)-[t]->(m) where 1=1 return p limit 20";
        log.info("[searchSQL]"+searchSQL);
        long start = System.currentTimeMillis();
        List<Love> loveList = new ArrayList<>();
        Map<String, String> parameters = new HashMap<>();
        Iterable<Love> loves = session.query(Love.class, searchSQL, parameters);
        if (loves != null) {
            for (Love love : loves) {
                loveList.add(love);
            }
        }
        log.info("[UseTime]"+(System.currentTimeMillis()-start)+"ms"+"[Size]"+loveList.size());
        return loveList;
    }

    public Long getNodeCount() {
        Long resultNum = 0L;
        try {
            StringBuffer cypher = new StringBuffer();
            Map<String, String> parameters = new HashMap<>();
            cypher.append("match (n:Person) return count(n)");
            Iterator<Map<String, Object>> mailSends = session.query(cypher.toString(), parameters).iterator();
            if (mailSends.hasNext()) {
                Map<String, Object> result = mailSends.next();
                resultNum = (Long)result.get("count(n)");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return resultNum;
    }

    public Long getRelationshipCount() {
        Long resultNum = 0L;
        try {
            StringBuffer cypher = new StringBuffer();
            Map<String, String> parameters = new HashMap<>();
            cypher.append("match p=(n:Person)-[]->(m:Person) return count(n)");
            log.info(cypher.toString());
            Iterator<Map<String, Object>> mailSends = session.query(cypher.toString(), parameters).iterator();
            if (mailSends.hasNext()) {
                Map<String, Object> result = mailSends.next();
                resultNum = (Long)result.get("count(n)");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return resultNum;
    }
}
