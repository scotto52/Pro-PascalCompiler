/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package propascal.transcompiler;

/**
 *
 * @author Robertson
 */

public class PascalParseError extends Exception 
{
    public PascalParseError(String message) {
        super(message);
    }
}
