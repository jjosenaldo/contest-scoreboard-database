/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maratona2.exception;

/**
 *
 * @author josenaldo
 */
public class InvalidFieldValueException extends RuntimeException
{
    private final String message;
    
    public InvalidFieldValueException(String s) {
          super(s);
          this.message = s;
    }
    
    @Override
    public String getMessage()
    {
        return this.message;
    }
}
