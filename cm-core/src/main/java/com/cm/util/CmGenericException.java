package com.cm.util;

public class CmGenericException extends Exception {

    public CmGenericException() {
    }

    public CmGenericException(Exception e) {
        super(e);
    }

    public CmGenericException(String message) {
        super(message);
    }

    public CmGenericException(String message, Exception e) {
        super(message, e);
    }
}
