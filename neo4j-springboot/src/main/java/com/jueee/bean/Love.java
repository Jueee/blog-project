package com.jueee.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "LOVES")
@Data
@NoArgsConstructor
public class Love {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person startNode;

    @EndNode
    private Person endNode;
}


