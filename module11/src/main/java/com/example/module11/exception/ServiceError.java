package com.example.module11.exception;

import lombok.Data;

@Data
public class ServiceError {

    private int status;
    private String details;
    private long timeStamp;
}
