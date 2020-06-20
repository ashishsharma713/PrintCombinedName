package com.Test.PrintCombinedName.PrintNameController;

import com.Test.PrintCombinedName.Entity.Name;
import com.Test.PrintCombinedName.Entity.Response;
import com.Test.PrintCombinedName.Entity.ResponseClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class PrintCombinedNameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintCombinedNameController.class);
    @Autowired
    private Response response;
    @Autowired
    private ResponseClass finalName;

    @PostMapping(path = "getName", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> getCombinedResponse(@Validated @RequestBody Name name) {
        finalName=new ResponseClass();
        try {
            LOGGER.debug("Calling the name service" + response.toString());
            response=new Response();
            String combinedName = response.returnFinalName(name);
            finalName.setResponse(combinedName);

        }
        catch(Exception e)
        {
            //LOGGER.error(e.getMessage());
            response.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(response.getErrorCode());
        }

        return new ResponseEntity<>(finalName,HttpStatus.OK);
    }

    @GetMapping(path = "health", produces = "application/json")
    public ResponseEntity<String> returnStatus() {

        //LOGGER.debug("Rest Request Health Check");
        return  new ResponseEntity<>("{\"status\":\"UP\"}", HttpStatus.OK);

    }
}
