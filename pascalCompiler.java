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
              "PROGRAM HelloWorld ; \n"+
              "CONST\n"+
              "D = 5:REAL;"+
              "VAR\n"+
              "A,B,C = 4:REAL;"+
              "Bigvarname:INTEGER;"+
              " BEGIN \n" +
              " A := 43 * 33 ; \n" +
              " B := C * 6 ; \n" +
              " Writeln(A) ; \n" +
              " Writeln(B) ; \n" +
              " END ." ;
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
