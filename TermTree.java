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
class TermTree extends TreePart
{ 
   TreePart before; 
   TreePart after ; 
   int symbol ; // * 
   
   public TermTree( TreePart fore , int action , TreePart after ) 
   { 
     assert ( action == TIMES || action == DIVIDE ||  action ==  PLUS || action ==  MINUS ) ;  
     this.before = fore ; 
     this.symbol = action;
     this.after  = after; 
   } 
   // the code for times and minus is quite simple. 
   public String toJavaCode()
   { 
       return  "(" + before.toJavaCode() + " " + (char)  symbol +
         " " + after.toJavaCode()+ ")"; 
   } 
   public static  final int  TIMES = '*'; 
   public static  final int  DIVIDE = '/' ; 
   public static final int  PLUS = '+' ; 
   public static final int  MINUS = '-' ; 
} 
