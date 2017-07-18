/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

/**
 *
 * @author Robertson
 */
public class LabelPart extends TreePart{
    String labelName;
    
    public LabelPart (String theName)
    {
        labelName = theName.toLowerCase();
    }
    
    public String toJavaCode()
    {
        return "(LABEL) "+labelName+";";
    }
    
    public String getLabelName()
    {
        return labelName;
    }        
    
}
