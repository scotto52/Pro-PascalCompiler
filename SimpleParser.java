/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter2;
import java.io.*;
import java.util.*;


class SimpleParser
{ 
  HashMap<String,VariablePart> symbolTable = new HashMap<String,VariablePart>(); 
  
  
  //-----------------------------------------------------------------------
  public SimpleParser()
  { 
    
  } 
  //======================================================================
  // Utility function for subclasses. 
  NumberLiteral makeNumericLiteral( double number )
  {  
    return   new NumberLiteral( number );
  }
  //-----------------------------------------------------------------------
    /**
       In this function we look 
        * Factor  := 
                       constant 
                       variabel 
                       '(' expression ')' 
    */ 
  
    public WriteLnTree writelnStatement (StreamTokenizer st)
            throws PascalParseError,IOException
    {
        int token = st.nextToken();
        if(token == StreamTokenizer.TT_WORD)
        {
            if(!"WriteLn".equalsIgnoreCase(st.sval))
            {
                throw new
                    PascalParseError("Writeln not starting with Writeln");
            }
            // AT THIS POINT WE KNOW WE HAVE WRITELN
            token = st.nextToken();
            if(token != '(')
            {
                throw new PascalParseError("Writeln EXPECTED a '(' here ");
            }
            TreePart express = expression(st); //GET THE REAL WORK
            token = st.nextToken();
            if(token != ')')
            {
                throw new PascalParseError("Writeln EXPECTED a ')' here " + 
                        "Not enough closing brackets ) perhaps ? ");
            }
            token = st.nextToken();
            if(token != ';')
            {
                throw new PascalParseError("Writeln missing semi colon ';' ");
            }
            return new WriteLnTree(express);
        }
        assert false : "Unreachable in theory";
        return null;
    }
    
    public Statement allStatements (StreamTokenizer st)
            throws PascalParseError,IOException
    {
        int token = st.nextToken();
        if(token != StreamTokenizer.TT_WORD)
        {
            throw new PascalParseError("expected a statement here BOB");
        }
        if( "WriteLn".equalsIgnoreCase(st.sval))
        {
            st.pushBack();
            return writelnStatement(st);
        }
        // OK GOT HERE AND IT'S NOT A RESERVED WORD -.
        // should check VARS to see if Var but for now assume OK.
        st.pushBack();
        return assignmentStatement(st);
    }
    
    public Statement assignmentStatement (StreamTokenizer st)
            throws PascalParseError,IOException
    {
        int token = st.nextToken();
        if (token == StreamTokenizer.TT_WORD)
        {
            System.out.println( " VARIABLE " + st.sval);
            VariablePart var = makeVariable(st.sval);
            
            token = st.nextToken();
            if (token != ':')
            {
                throw new PascalParseError("EXPECTED := in assignment ");
            }
            token = st.nextToken();
            if(token !='=')
            {
                throw new PascalParseError("EXPECTED := in assignment");
            }
            TreePart express = expression( st );
            
            token = st.nextToken();
            if(token != ';')
            {
                throw new PascalParseError("EXPECTED ; at end of statement ");
            }
            return new AssignmentTree(var, express);
        }else
        {
            throw new PascalParseError("EXPECTED variable name in expression");
        }
    }
    
    public TreePart factor( StreamTokenizer st)  throws PascalParseError,IOException 
    { 
      int token = st.nextToken();
      if(  token == StreamTokenizer.TT_WORD ) 
      { 
         System.out.println( " VARIABLE " + st.sval ) ; 
         return makeVariable(st.sval ); 
      } 
      if(  token ==  StreamTokenizer.TT_NUMBER ) 
      { 
        System.out.println( " NUMBER "  + st.nval);
        return  makeNumericLiteral( st.nval ) ; 
      }
      if(  token ==  '('  ) // HANDLE EXPRESSION 
      { 
        System.out.println( " NUMBER "  + st.nval);
        TreePart e = expression ( st ) ; 
        
        token = st.nextToken();
        if( token != ')' ) 
        { 
             throw  new PascalParseError("Was expecting ending ) " ); 
        } 
        return e ;       
      } 
     throw  new PascalParseError("Was expecting number" ); // NO IDEA - just compliane 
    }
    //----------------------------------------------------------
    // SPERATE SO YOU CAN OVER RIDE IT. 
    TermTree makeTermLiteral(TreePart fore , int action , TreePart after )
    {  
      return  new TermTree(  fore ,  action ,  after );
    }
    //----------------------------------------------------------
    public TreePart term( StreamTokenizer st)  throws PascalParseError,IOException 
    { 
      TreePart first = factor( st ) ; // Get factor 
      int token = st.nextToken();
      
      System.out.println(  " TOKEN " +  token  + " " + (st.TT_WORD  == token) ); 
      
      while( token == TermTree.TIMES || token == '/'   ) 
      { 
        TreePart second = factor( st ) ;
        first =   makeTermLiteral(first , token  , second ) ;
        token = st.nextToken();// CHECK NEXT ONE. 
      // should also parse expression. 
         System.out.println(  " TOKEN (2) " +  token  + " " + (st.TT_WORD  == token) );
      
      }
      System.out.println("NOT TERM jump back"); 
      st.pushBack();  // Leave it for somone. else  
      return first; // nothing else to 
    }
    
    //----------------------------------------------------------
    public TreePart expression( StreamTokenizer st)  throws PascalParseError,IOException 
    { 
      TreePart first = term( st ) ; // Get factor 
      int token = st.nextToken();
      
      System.out.println(  " EXPR " +  token  + " " + (st.TT_WORD  == token) ); 
      
      while( token == TermTree.PLUS || token == TermTree.MINUS ) 
      { 
        TreePart second = term( st ) ;
        first =   makeTermLiteral(first , token  , second ) ;
        token = st.nextToken();// CHECK NEXT ONE. 
     
         System.out.println(  " EPX (2) " +  token  + " " + (st.TT_WORD  == token) );
      
      }
      System.out.println("NOT EXPR jump back"); 
      st.pushBack();  // Leave it for somone. else  
      return first; // nothing else to 
    } 
      //----------------------------------------------------------
      VariablePart makeVariable(String name  )
      {  
          name = name.toLowerCase(); //PASCAL IS CASE SENSITIVE
         VariablePart var = symbolTable.get( name ) ; 
        if( var  == null  ) 
        {  // NOT FOUND MAKE A NEW ONE
           var =  new VariablePart( name );
           symbolTable.put( name , var ) ; 
        } 
        return var  ;
      } 
      //----------------------------------------------------------
      public TreePart parseStart(  BufferedReader in4)  throws PascalParseError, IOException 
      { 
        StreamTokenizer st = new StreamTokenizer(in4);
        st.slashStarComments(false); 
        st.slashSlashComments(false);
        st.eolIsSignificant(false);
        st.ordinaryChar('/'); 
        st.parseNumbers();
      
        Statement result = parseProgram( st ) ;//CHAGEN 
        return result ; 
      } 
      
      public String expressionToJava (BufferedReader in4) throws PascalParseError, IOException
      {
          TreePart result = this.parseStart(in4);
          String javaCode = "// GENERATED CODE \n";
          
          //for (Map.Entry<String,VariablePart> it: symbolTable.entrySet())
          //{
          //    javaCode += it.getValue().getDefinitionString();
          //}
          javaCode += "\n" + result.toJavaCode();
          
          return javaCode;
      }
      
      public void parseConsts(StreamTokenizer st, BlockStatement block)
              throws PascalParseError,IOException
      {
          // do rows
          String theNext = "";
          do
          {
          // PARSE OPTION BEGIN
          int token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError(
              "expected a Constant Name here" + token);
          }
          if ("BEGIN".equalsIgnoreCase(st.sval) == true)
          {
              st.pushBack();
              return ;
          }
          
          ConstantPart c = new ConstantPart(st.sval);
          ArrayList<ConstantPart> px = new ArrayList<ConstantPart>();
          px.add(c);
          if(block.addConstant(c)== false)
          {
              throw new PascalParseError("Duplicate Constant name "+st.sval);
              }
              // PARSE '='
              token = st.nextToken();
              if(token != '=')
              {
                  throw new PascalParseError("expected an equals '=' here");
              }
              token = st.nextToken();
              System.out.println("= so READ number ");
              if(token == StreamTokenizer.TT_NUMBER )
              {
                  System.out.println( " NUMBER " + st.nval);
                  c.setValue(st.nval);
                  token = st.nextToken();
              }else
              {
                  throw new PascalParseError("Expected a number ");
              }
              // PARSE ':' to end list
              if(!(token == ':'))
              {
                  throw new PascalParseError("expected a ':' here");
          } while (token != ':');

              token = st.nextToken();
              if (token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected a Constant Type here ");
              }
              // This time
              ConstantType type = ConstantType.getEnumerationForString(st.sval);
              for(ConstantPart theConst : px)
              {
                  theConst.setConstant(type);
              }
              token = st.nextToken();
              if(token != ';')
              {
                  throw new PascalParseError("expected semi colon here ");
              }
              token = st.nextToken();
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected BEGIN/VARIABLE name here ");
              }
              theNext = new String(st.sval);
              st.pushBack();
          } while(theNext.equalsIgnoreCase("VAR") == false);
              System.out.println("FINISHED CONSTS");
              
              
              
              
          }
      
      
      public void parseVars( StreamTokenizer st, BlockStatement block)
              throws PascalParseError, IOException
      {
          // do rows
          String theNext = "";
          do // do a line of variables
          {
          // PARSE OPTION BEGIN
          int token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ) //[1]
          {
              throw new PascalParseError(
              "expected a Variable Name here got '" + token);
          }
          if( "BEGIN".equalsIgnoreCase(st.sval) == true)
          {
              st.pushBack();
              return ;
          }
          
          VariablePart t = new VariablePart(st.sval);
          ArrayList<VariablePart> px = new ArrayList<VariablePart>();
          px.add(t);
          st.pushBack();
          do
          {
              //PARSE VARIABLE NAME
              token = st.nextToken();
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected a Variable Name here ");
              }
              System.out.println("Found "+ st.sval);
              t = new VariablePart(st.sval);
              px.add(t);
              if(block.addVariable(t)== false)
              {
                  throw new PascalParseError("Duplicate Variable name "+ st.sval);
              }
              token = st.nextToken();
              if( token == '=') // handle deafult value
              {
                  token = st.nextToken();
                  System.out.println("= so READ number");
                  if(token == StreamTokenizer.TT_NUMBER)
                  {
                      System.out.println(" NUMBER " + st.nval);
                      t.setDefaultValue(st.nval);
                      token = st.nextToken();
                  }else
                  {
                      throw new PascalParseError("Expected a number ");
                  }
              }
              // PARSE ',' or ':' to end list
              if(! (token == ':' || token == ',' ))
              {
                  throw new PascalParseError("expected a comma ',' or ':' here");
              }
              
          }while(token != ':');
          
          token = st.nextToken();
          // PARSE NAME OF VARIABLE MUST BE 'REAL'
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected a Variable Type here ");
          }
          // This time
          VariableType type = VariableType.getEnumerationForString(st.sval);
          for(VariablePart theVar : px)
          {
              theVar.setVariable(type);
          }
          token = st.nextToken();
          if (token != ';')
          {
              throw new PascalParseError("expected semi colon here ");
          }
          token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected BEGIN/VARIABLE name here ");
          }
          theNext = new String (st.sval);
          st.pushBack();
          }while( theNext.equalsIgnoreCase("BEGIN")  == false);
          System.out.println(" FINISHED VARS");
      }

      public Statement parseBlock(StreamTokenizer st)
              throws PascalParseError,IOException
      {
          BlockStatement block = new BlockStatement(); assert block != null;
          int token = st.nextToken();
          // Check existance of a constant or variable
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected a BEGIN or CONST or VAR here ");
          }
          if ("CONST".equalsIgnoreCase(st.sval) == true)
          {
              parseConsts(st, block);
              token = st.nextToken();
          }
          if ("VAR".equalsIgnoreCase(st.sval) == true)
          {
              parseVars(st, block);
              token = st.nextToken();
          }
          //[1] PARSE BLOCK
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected a BEGIN here at this block");
          }
          if("BEGIN".equalsIgnoreCase(st.sval) == false)
          {
              throw new PascalParseError("Block must begin with word BEGIN ");
          }
          // [2] LOOP PARSING STATEMENTS

          boolean keepGoing = true;
          do // ensures loop runs at least once
          {
              Statement s = allStatements(st);
              block.add(s);
          
          // [3] PARSE 'END'
          token = st.nextToken();
          if (token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected an END here at this block");
          }
          if ("END".equalsIgnoreCase(st.sval)== true)
          {
              keepGoing = false;
          }else
          {
              st.pushBack();
          }
      }while(keepGoing == true);
      return block;
      }
      
      public Statement parseProgram(StreamTokenizer st)
              throws PascalParseError, IOException
      {
          // ProgramStatement
          // [1] GET PROGRAM
          int token = st.nextToken();
          
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("Programs begin with PROGRAM");
          }
          
          //[2] GET IDENTIFIER (program name)
          token = st.nextToken();
                  if(token != StreamTokenizer.TT_WORD)
                  {
                      throw new PascalParseError("Programs begin with PROGRAM <IDENTIFIER>");
                  }
                  String programName = new String(st.sval); //make copy of program name.
                  System.out.println("Program is "+ programName);
                  
            //[3] Get ; after IDENTIFIER.
            token = st.nextToken();
            if(token != ';')
            {
                throw new PascalParseError("Expected <identifier> ; ... ");
                  }
            Statement s = parseBlock(st); // GET THE BLOCK
            st.ordinaryChar('.'); // HACK
            token = st.nextToken();
            if(token != '.')
            {
                throw new PascalParseError("Programs end with END . (space full stop)");
            }
            ProgramStatement prgm = new ProgramStatement(programName, s);
            return prgm;
            
      }
} // END CLASS 
 

