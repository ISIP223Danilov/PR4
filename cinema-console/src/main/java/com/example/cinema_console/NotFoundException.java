package com.example.cinema_console;

// Собственное исключение для обработки ситуаций, когда объект не найден
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}