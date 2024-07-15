package com.example.crud_operation.exception;

import java.util.Date;

public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String details, String message) {
        super();
        this.timestamp = timestamp;
        this.details = details;
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }
}
