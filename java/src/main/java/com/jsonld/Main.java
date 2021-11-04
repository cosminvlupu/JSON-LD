package com.jsonld;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jsonld.service.obj.Car;
import com.jsonld.service.obj.OtherInfo;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import ioinformarics.oss.jackson.module.jsonld.JsonldResource;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OtherInfo otherInfo = new OtherInfo();
        otherInfo.setNumberOfAirbags(4);
        otherInfo.setNumberOfDoors(2);

        Car car = new Car();
        car.setId("1");
        car.setColor("red");
        car.setFabricationYear("1997");
        car.setOtherInfo(otherInfo);

        ObjectMapper jsonldObjectMapper = new ObjectMapper();
        jsonldObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jsonldObjectMapper.registerModule(new JsonldModule(jsonldObjectMapper::createObjectNode));

        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        String jsonld = jsonldObjectMapper.writer().writeValueAsString(JsonldResource.Builder.create().build(car));

        //serealisation

        System.out.println("json:\n" + jsonObjectMapper.writeValueAsString(car));  //as JsonString

        System.out.println("JsonLd: \n" + jsonldObjectMapper.writer().writeValueAsString(JsonldResource.Builder.create().build(car)));  //as Jsonld String

        System.out.println("Json Obj" + jsonObjectMapper.reader().readValue(jsonld, Car.class));  //as java Object (Car)

        System.out.println("Json Obj" + jsonldObjectMapper.reader().readValue(jsonld, Car.class));
    }
}
