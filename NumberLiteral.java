/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;

//======================================================================
class NumberLiteral extends  TreePart
{ 
  double literal = 0.0 ; 
  public NumberLiteral( double it ) 
  { 
    this. literal = it;
  } 
 public String toString(){ return toJavaCode(); } 
 public String toJavaCode() 
 { 
    return ""+literal  ;
  }
} 