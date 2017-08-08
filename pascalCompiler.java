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
              "[INHERIT('SYS$LIBRARY:STARLET')] \n" + 
              "PROGRAM HelloWorld ; \n"+
              "LABEL\n"+
              "ERRLAB;"+
              "CONST\n"+
              "D = 5;"+
              " (* THIS IS A COMMENT TEST *) "+ 
              "VAR\n"+
              " A:BOOLEAN;"+
              " B:REAL;"+
              " Bigvarname:INTEGER;"+
              "[EXTERNAL,ASYNCHRONOUS] \n" +
              " BEGIN \n" +
              " B := D * 6 ; \n" +
              " IF 43 > 33 AND 4 > 1 THEN \n"+
              "     Writeln( A ) ; \n"+
              " ELSE \n"+
              "     Writeln( B ) ; \n"+
              " Bigvarname := 1; "+
              " REPEAT \n "+
              "     Writeln(Bigvarname) ; \n" +
              "     Bigvarname : = Bigvarname +1 ; \n" +
              " UNTIL Bigvarname > 10; " +
              " Writeln( Bigvarname) ;"+
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
