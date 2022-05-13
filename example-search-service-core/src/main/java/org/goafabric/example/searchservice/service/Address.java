package org.goafabric.example.searchservice.service;

import lombok.*;

@Value
@Builder
public class Address {
    private String id;

    private String street;
    private String city;
}
