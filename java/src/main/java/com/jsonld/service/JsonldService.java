package com.jsonld.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jsonld.service.obj.Car;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import org.springframework.stereotype.Service;

@Service
public class JsonldService {

    private ObjectMapper jsonldObjectMapper = new ObjectMapper();

    private ObjectMapper jsonObjectMapper = new ObjectMapper();

    public String transformJsonldtoJson(Car car) throws JsonProcessingException {
        jsonldObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        return jsonldObjectMapper.writeValueAsString(car);
    }

    public String transformJsonToJsonld(Car car) throws JsonProcessingException {
        jsonObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jsonObjectMapper.registerModule(new JsonldModule(jsonldObjectMapper::createObjectNode));

        return jsonObjectMapper.writeValueAsString(car);
    }

}
