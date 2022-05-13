package org.goafabric.example.searchservice.service;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;

    private String firstName;
    private String lastName;

    private Address address;

    private List<Skill> skills;
}
