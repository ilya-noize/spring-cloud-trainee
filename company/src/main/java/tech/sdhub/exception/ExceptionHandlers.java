package tech.sdhub.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    private void logError(HttpStatus status, String error, ZonedDateTime dateTime, Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTraceString = sw.toString().replace(", ", "\n");

        log.error("[!] Received the status {}. Error: {}. Time: {}.\n{}", status, error, dateTime, stackTraceString);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerError(Throwable throwable) {
        String message = throwable.getMessage();
        ZonedDateTime now = ZonedDateTime.now();
        HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        logError(badRequest, message, now, throwable);

        return new ErrorResponse(badRequest.value(), message, now);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException e) {
        String message = e.getMessage();
        ZonedDateTime now = ZonedDateTime.now();
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        logError(badRequest, message, now, e);

        return new ErrorResponse(badRequest.value(), message, now);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        String message = e.getMessage();
        ZonedDateTime now = ZonedDateTime.now();
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        logError(badRequest, message, now, e);

        return new ErrorResponse(badRequest.value(), message, now);
    }
}
