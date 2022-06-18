package ru.itmo.errors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rozov
 */
public class CastToIntException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;

    public static CastToIntException DEFAULT_INSTANCE = new CastToIntException("Some fields values cannot be convert to integers");

    public CastToIntException(String message) {
        super(message);
    }
}
