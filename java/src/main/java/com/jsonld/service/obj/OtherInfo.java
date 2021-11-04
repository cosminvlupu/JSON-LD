package com.jsonld.service.obj;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonldType("http://schema.org/OtherInfo")
public class OtherInfo {

    @JsonldProperty("http://schema.org/numberOfAirbags")
    public int numberOfAirbags;

    @JsonldProperty("http://schema.org/numberOfDoors")
    public int numberOfDoors;

    @JsonldProperty("http://schema.org/numberOfPreviousOwners")
    public int numberOfPreviousOwners;
}
