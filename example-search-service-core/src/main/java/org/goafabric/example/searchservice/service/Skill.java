package org.goafabric.example.searchservice.service;


import lombok.*;

@Value
@Builder
public class Skill {
    private String id;
    private String name;
    private String description;
}
