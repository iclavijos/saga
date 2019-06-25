package com.mimacom.ice.saga.warehouse.exception;

public class NotEnoughStockException extends Error {

    public NotEnoughStockException(String message) {
        super(message);
    }
}
