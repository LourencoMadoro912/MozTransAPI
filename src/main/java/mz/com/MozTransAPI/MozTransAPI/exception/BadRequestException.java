package mz.com.MozTransAPI.MozTransAPI.exception;

public class BadRequestException  extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
