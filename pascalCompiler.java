/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

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
        BufferedWriter writer = null;
        try 
    {
      BufferedReader in4;
      in4 = new BufferedReader(new FileReader("C:/Users/Robertson/Documents/NetBeansProjects/Chapter6/src/chapter2/TestPascal.txt"));
      
      SimpleParser myParser = new SimpleParser(); 
      assert myParser != null ; 
      String javacodeString = myParser.expressionToJava(in4);
      System.out.println("PARSE OK //------\n" + "\n/------");
      writer = new BufferedWriter(
                    new FileWriter("C:/Users/Robertson/Documents/NetBeansProjects/Chapter6/src/chapter2/JavaOutput.java"));
      writer.write(javacodeString);
      System.out.println("FILE SAVED OK");
     } 
    catch(EOFException e)
    {
      System.out.println("End of stream");
    }
    catch( IOException e )
    { 
      System.out.println("IO Exception " + e);
    }
    catch( PascalParseError error ) 
    { 
       System.out.println("Eerror " + error + " Line " ) ; 
    } 
        finally
        {
            try
            {
                if (writer != null)
                    writer.close();
            }
            catch (IOException e)
            {
                System.out.println("Error " + e);
            }
        }
    }
    
    
  
    
}
