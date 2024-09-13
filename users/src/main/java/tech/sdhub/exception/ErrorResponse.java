package tech.sdhub.exception;

import lombok.AllArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String error;
    private final ZonedDateTime dateTime;
}
