package com.jsonld.service.obj;

import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldId;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldProperty;
import ioinformarics.oss.jackson.module.jsonld.annotation.JsonldType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonldType("http://schema.org/Car")
public class Car {

    @JsonldId
    public String id;

    @JsonldProperty("http://schema.org/model")
    public String model;

    @JsonldProperty("http://schema.org/fabricationYear")
    public String fabricationYear;

    @JsonldProperty("http://schema.org/color")
    public String color;

    @JsonldProperty("http://schema.org/fuelCapacity")
    public int fuelCapacity;

    @JsonldProperty("http://schema.org/otherInfo")
    public OtherInfo otherInfo;
}
