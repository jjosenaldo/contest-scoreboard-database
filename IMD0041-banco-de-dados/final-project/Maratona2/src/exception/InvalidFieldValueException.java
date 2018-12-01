/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author josenaldo
 */
public class InvalidFieldValueException extends RuntimeException
{
    public InvalidFieldValueException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
