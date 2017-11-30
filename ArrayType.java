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
public class ArrayType extends TypePart {
    
    TypePart ofWhat; //what is it an array of
    int lowRange = 0;
    int highRange = Integer.MAX_VALUE;
    
    public ArrayType(int lowRange, int upperRange, TypePart ofWhat) {
        this.lowRange = lowRange;
        this.highRange = upperRange;
        this.ofWhat = ofWhat;
        assert ofWhat != null;
    }
    
    public String toJavaCode(){
        return ofWhat.toJavaCode() + "[ ]";
    }
}

