package org.goafabric.example.searchservice.controller.dto;


import java.util.List;

public record Person (
    String id,
    String firstName,
    String lastName,
    List<Address> address,
    List<Skill> skills
) {}
