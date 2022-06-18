/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.itmo.errors;

/**
 *
 * @author rozov
 */
public class EmptyFieldException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;

    public static EmptyFieldException DEFAULT_INSTANCE = new EmptyFieldException("Some fields cannot be null or empty");

    public EmptyFieldException(String message) {
        super(message);
    }
}