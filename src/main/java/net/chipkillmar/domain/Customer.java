package net.chipkillmar.domain;

import lombok.Data;

@Data
public class Customer {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String notes;
    private Address address;
}
