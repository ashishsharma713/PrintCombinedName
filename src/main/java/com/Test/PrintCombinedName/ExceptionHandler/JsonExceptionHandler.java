package com.Test.PrintCombinedName.ExceptionHandler;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class JsonExceptionHandler extends ResponseEntityExceptionHandler {

   @ResponseBody
    ResponseEntity<Object> handleControllerException(HttpServletRequest request,Throwable ex)
   {
       Map<String,String> responseBody=new HashMap<>();
       responseBody.put("JSON" ,ex.getMessage());
      return new ResponseEntity<Object>(responseBody,HttpStatus.BAD_REQUEST);
   }

}
