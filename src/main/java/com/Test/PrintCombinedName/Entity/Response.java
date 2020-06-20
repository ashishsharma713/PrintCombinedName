package com.Test.PrintCombinedName.Entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class Response {


    private static final Logger LOGGER = LoggerFactory.getLogger(Response.class);




    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    private HttpStatus errorCode;

    //Method to return the final Name
    public String returnFinalName(Name name) {


        RestTemplate restTemplate = new RestTemplate();
        JsonParser parse=JsonParserFactory.getJsonParser();
        Map<String,Object> mp;
        String response = restTemplate.getForObject("http://localhost:8080/api/hello", String.class);
        String finalName = restTemplate.postForObject("http://localhost:8081/api/name", name, String.class);
        mp=parse.parseMap(response);
        for(Map.Entry<String,Object> entry:mp.entrySet())
        {
            response=entry.getValue().toString();
        }
        mp=parse.parseMap(finalName);
        for(Map.Entry<String,Object> entry:mp.entrySet())
        {
            finalName=entry.getValue().toString();
        }

        return response + " " + finalName;


    }
}
