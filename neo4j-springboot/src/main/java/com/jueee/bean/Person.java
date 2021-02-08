package com.jueee.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Person")
@Data
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    Long id;

    @Property(name = "name")
    private String name;
}


