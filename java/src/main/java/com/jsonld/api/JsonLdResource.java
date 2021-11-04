package com.jsonld.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jsonld.service.JsonldService;
import com.jsonld.service.obj.Car;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
public class JsonLdResource {

    @Autowired
    JsonldService transformationService;

    @GetMapping
    @RequestMapping(value = "/transform/jsonld", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity <String> transformJsonldToJson(@RequestBody Car jsonld) throws JsonProcessingException {

        return ResponseEntity.ok(transformationService.transformJsonldtoJson(jsonld));
    }

    @GetMapping
    @RequestMapping(value = "/transform/json", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    ResponseEntity <String> transformJsonToJsonld(@RequestBody Car json) throws JsonProcessingException {

        return ResponseEntity.ok(transformationService.transformJsonToJsonld(json));
    }
}
