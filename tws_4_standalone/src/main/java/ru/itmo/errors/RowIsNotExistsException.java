/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.itmo.errors;

/**
 *
 * @author rozov
 */
public class RowIsNotExistsException extends Exception {
    private static final long serialVersionUID = -6647544772732631047L;

    public static RowIsNotExistsException DEFAULT_INSTANCE = new RowIsNotExistsException("Row with this ID does not exist.");

    public RowIsNotExistsException(String message) {
        super(message);
    }
}