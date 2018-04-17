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
public class Statement extends TreePart // Marker class
{
    public String stringPascalComment = null;
    
    public Statement()
    {
        super();
    }
    
    public String toJavaCode()
    {
        if(stringPascalComment == null) return "";
        return "/* " + stringPascalComment + "*/ ";
    }
}

