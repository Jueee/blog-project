package com.jueee.service;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;

@Slf4j
public class HelloService {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://neo4j.host.address:7687", AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();
        final Result result = session.run("MATCH (a:Person) RETURN a.name AS name");
        while (result.hasNext()) {
            Record record = result.next();
            System.out.println(record.get("name").asString());
        }
        session.close();
        driver.close();
    }
}
