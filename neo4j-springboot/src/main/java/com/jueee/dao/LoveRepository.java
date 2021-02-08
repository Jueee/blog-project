package com.jueee.dao;

import com.jueee.bean.Love;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface LoveRepository extends Neo4jRepository<Love,Long> {

}

