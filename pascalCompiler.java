/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author nd2563
 */
public class pascalCompiler {

    /**
     * @param args the command line arguments
     */

        
    public static void main(String[] args) {
        // TODO code application logic here
        try 
    {
      String sourceCode = 
              "[INHERIT('SYS$LIBRARY:STARLET')] " + 
              "PROGRAM HelloWorld ; \n"+
              "[INHERIT('SYS$LIBRARY:STARLET')] " +
              "CONST\n"+
              "Failure = 0; \n" +
              " (* THIS IS A COMMENT TEST *) \n" + 
              "TYPE\n" +
              " String255 = Array[0..255] of INTEGER; \n " +
              " Colour = Record \n" +
              "             red:Integer; \n" +
              "             green:Integer; \n" +
              "             blue:Integer; \n" +
              "         End; \n" +
              "VAR\n"+
              " A:BOOLEAN;\n"+
              " Bigvarname:Array[0..10] of INTEGER; \n"+
              " BEGIN \n" +
              " Bigvarname[6] := 1 * 2 + Bigvarname[1+1]; (* THIS IS A COMMENT AGAIN *) \n "+
              "\n"+
              " IF 43 > 33 AND 4 > 1 THEN \n"+
              " BEGIN "+
              "     Writeln( A * Failure) ; \n"+
              "     Writeln( 5*7 ) ; \n"+
              " END "+
              " Writeln(Bigvarname) ; " +
              "END ." ;
      BufferedReader in4 = new BufferedReader(new StringReader(sourceCode));
      
      SimpleParser myParser = new SimpleParser(); 
      assert myParser != null ; 
      String javacodeString = myParser.expressionToJava(in4);
      System.out.println("PARSE OK "+ javacodeString); 
     } 
    catch(EOFException e)
    {
      System.out.println("End of stream");
    }
    catch( IOException e )
    { 
      System.out.println("IO Exception ");
    }
    catch( PascalParseError error ) 
    { 
       System.out.println("Eerror " + error + " Line " ) ; 
    } 
    }
    
    
  
    
}
