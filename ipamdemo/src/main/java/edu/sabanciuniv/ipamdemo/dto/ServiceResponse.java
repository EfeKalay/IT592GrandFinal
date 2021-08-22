package edu.sabanciuniv.ipamdemo.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

@JsonIgnoreProperties(value = { "status" })
public class ServiceResponse {

    HttpStatus status;
    String message;
    Object response;

    public ServiceResponse(HttpStatus status, String message, Object response){
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public static ServiceResponse defaultInternalError(){
        return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error", null);
    };

    public static ServiceResponse defaultInternalError(String message){
        return new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR,message,null);
    };

    public static ServiceResponse defaultInvalidRequest(String message){
        return new ServiceResponse(HttpStatus.BAD_REQUEST,message,null);
    };

}
