
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

import java.io.*;
import java.io.StreamTokenizer;
import static java.io.StreamTokenizer.*;

/**
 *
 * @author Robertson
 */
public class ProPascalStreamTokenizer extends StreamTokenizer{
    String lastComment = null;
    
    public ProPascalStreamTokenizer(Reader input)
    {
        super(input);
        super.parseNumbers();
        super.slashStarComments(false);
        super.eolIsSignificant(false);
        super.ordinaryChar('/');
    }
    
    public String getLastComment()
    {
        String s = lastComment;
        lastComment = null;
        return s;
    }
    
    private void readUntilEndofComment() throws IOException
    {
        int nextTKN;
        System.out.println(" found start of comment ");
        
        lastComment = "(* ";
        boolean stillInComment = true;
        do
        {
            nextTKN = super.nextToken();
            switch(nextTKN)
            {
                case TT_WORD: lastComment += (super.sval + " "); break;
                case TT_NUMBER: 
                {
                    String numberAsString = Double.toString(super.nval);
                    lastComment += (numberAsString += " ");
                } break;
                case TT_EOF:
                {
                    lastComment += " ERROR ENDED IN EOF";
                    stillInComment = false;
                    System.out.println("END END END");
                    return;
                }
                default :
                {
                    lastComment += (char)nextTKN;
                    if(nextTKN == '*') //check for ending
                    {
                        nextTKN = super.nextToken();
                        lastComment += (char)nextTKN; // add it any way
                        if(nextTKN == ')')
                        {
                            System.out.println("Done comment " + lastComment);
                            stillInComment = false;
                        }
                    }
                }
            }
        }while (stillInComment == true);
    }
    //override nextToken to ignore Pascal Comments
    @Override public int nextToken() throws IOException
    {
        int tkn = super.nextToken(); // normal process
        if(tkn == '(') // could be a bracket for comment
        {
            int nextTKN = super.nextToken();
            if(nextTKN == '*' ) // have found (* pascal comment start
            {
                //skip over everything
                readUntilEndofComment();
                return super.nextToken(); //normal process
            }else
            {
                super.pushBack(); //push nextTKN the * baack for later
                return '(';
            }
        }
        return tkn; // BASIC
    }
    
    public static void main(String[] args)
    {
        try
        {
            String parseStream = "HELLO (* 1.5 *) WORLD (2 * 3) ";
            BufferedReader in4 = new BufferedReader(new StringReader(parseStream));
            ProPascalStreamTokenizer testStream = new ProPascalStreamTokenizer(in4);
            int t = testStream.nextToken();
            while(t != ProPascalStreamTokenizer.TT_EOF)
            {
                switch(t)
                {
                    case TT_WORD: System.out.println(testStream.sval); break;
                    case TT_NUMBER: System.out.println(testStream.nval); break;
                    case TT_EOF: System.out.println("ERROR ENDED IN  EOF"); break;
                    default :
                    {
                        System.out.println((char) t); break;
                    }
                }
                t=testStream.nextToken();
            }
            System.out.println("Done...");
        } catch (IOException ex)
        {
            System.out.println("ERROR");
        }
    }

}
