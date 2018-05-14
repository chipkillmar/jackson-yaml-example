package net.chipkillmar.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Address {
    private String street;
    private String city;
    private State state;
    @JsonProperty("zip")
    private int zipCode;
}
