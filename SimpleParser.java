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
  HashSet<LabelPart> labelSet = new HashSet<LabelPart>();
  
  
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
            throw new PascalParseError("expected a statement here");
        }
        if( "BEGIN".equalsIgnoreCase(st.sval))
        {
            st.pushBack();
            return groupStatement(st);
        }
        if( "IF".equalsIgnoreCase(st.sval))
        {
            st.pushBack();
            return ifStatement(st);
        }
        if( "WHILE".equalsIgnoreCase(st.sval))
        {
            st.pushBack();
            return whileStatement(st);
        }
        if( "REPEAT".equalsIgnoreCase(st.sval))
        {
            st.pushBack();
            return repeatStatement(st);
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
            st.pushBack();
            
            TreePart var = parseVariable(st);
            
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
            TreePart express = booleanGroupExpression( st );
            
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
         st.pushBack();
         return parseVariable(st); 
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
      LabelPart makeLabel(String name) throws PascalParseError, IOException 
      {
          name = name.toLowerCase();
          LabelPart lab = new LabelPart(name);
          boolean isIn = labelSet.contains(lab);
          if(isIn == true)
          {
           throw new PascalParseError("Label name already exists");
          }
              // NOT FOUND MAKE A NEW ONE
              labelSet.add(lab);
              return lab;
          

      }
      public TreePart parseStart(  BufferedReader in4)  throws PascalParseError, IOException 
      { 
        StreamTokenizer st = new ProPascalStreamTokenizer(in4);
        //st.slashStarComments(false); 
        //st.eolIsSignificant(false);
        //st.ordinaryChar('/'); 
        //st.parseNumbers();
        Statement result = null;
        try
        {
            result = parseProgram( st );
        } catch (PascalParseError e)
        {
            System.out.println("ERROR " + e + " LINE "+ st.lineno());
        }

        return result ; 
      } 
      
      public Statement groupStatement (StreamTokenizer st)
              throws PascalParseError,IOException
      {
          BlockStatement block = new BlockStatement(); assert block != null;
          int token = st.nextToken();
          if("BEGIN".equalsIgnoreCase(st.sval)== false)
          {
              throw new PascalParseError("Block must begin with word BEGIN");
          }
          
          boolean keepGoing = true;
          do
          {
              Statement s = allStatements(st);
              block.add(s);
              token = st.nextToken();
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected and END here at this block" + token);
              }
              if("END".equalsIgnoreCase (st.sval) == true)
              {
                  keepGoing = false;
                  System.out.println("FOUND END");
              }else
              {
                  st.pushBack();
              }
                  
              }while (keepGoing == true);
          return block;
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
      
      public void parseLabels (StreamTokenizer st, BlockStatement block)
              throws PascalParseError, IOException
      {
          int token;
          LabelPart l = null;
          boolean keepGoing = true;
          
          while(keepGoing == true)
          {
              token = st.nextToken();
              if("BEGIN".equalsIgnoreCase(st.sval) == true ||
                      "CONST".equalsIgnoreCase(st.sval) == true ||
                      "TYPE".equalsIgnoreCase(st.sval) == true ||
                      "VAR".equalsIgnoreCase(st.sval) == true)
              {// this is ok label can be empty list
                  st.pushBack();
                  keepGoing = false;
                  return;
              }
              // find a name for the label
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected a Label identifier here " + token);
              }
              System.out.println("Found label "+ st.sval);
              l = new LabelPart(st.sval);
              if(block.addLabel(l) == false)
              {
                  throw new PascalParseError("Duplicate Label name "+ st.sval);
              }
              token = st.nextToken();
              if(!(token == ';'))
              {
                  throw new PascalParseError("expected a comma ',' here");
              }

              System.out.println(" FINISHED LABELS ");
          }
          //assert false :"UNREACHABLE";
      }
      
      public void parseConsts(StreamTokenizer st, BlockStatement block)
              throws PascalParseError,IOException
      {
          // do rows
          //String theNext = "";
          //do
          //{
          // PARSE OPTION BEGIN
          int token;
          ConstantPart c = null;
          boolean keepGoing = true;
          // ASSUME CONST EXIST UNTIL WE FIND TYPE, VAR or BEGIN
          while(keepGoing == true)
          {
              token = st.nextToken();
            if("TYPE".equalsIgnoreCase(st.sval) == true ||
                    "VAR".equalsIgnoreCase(st.sval) == true ||
                            "BEGIN".equalsIgnoreCase(st.sval) == true)
                      { // this is OK const may not exist
              st.pushBack();
              keepGoing = false;
              return ;
          }
              //PARSE CONSTANT NAME
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected a Constant Name here");
              }
              System.out.println("Found constant "+ st.sval);
              c = new ConstantPart(st.sval);
              if(block.addVariable(c)== false)
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
              System.out.println("READ Constant Value");
              if(token == StreamTokenizer.TT_NUMBER)
              {
                  System.out.println("NUMBER " + st.nval);
                  c.setDefaultValue(st.nval);
                  token = st.nextToken();
              }else 
              {
                  // ToDo handle strings and characters and other types
                  throw new PascalParseError("Expected valid Constant Type");
              }
              if (! (token == ';') )
              {
                  throw new PascalParseError("expected a comma ',' or ':' ");
              }
              System.out.println("FINISHED CONSTS");
          }
        assert false : "UNREACHABLE";
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
              // PARSE ',' or ':' to end list
              if(! (token == ':' || token == ',' ))
              {
                  throw new PascalParseError("expected a comma ',' or ':' here");
              }
              
          }while(token != ':');
          
          VariableType type = parseInLineType(st, block);
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
          if(token == '[') token = parseCompilerHint(st, block);
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected BEGIN/VARIABLE name here ");
          }
          theNext = new String (st.sval);
          st.pushBack();
          }while( theNext.equalsIgnoreCase("BEGIN")  == false);
          System.out.println(" FINISHED VARS");
      }

      public VariableType parseInLineType(StreamTokenizer st, BlockStatement block)
              throws PascalParseError,IOException
      {
          int token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected a Variable Type here");
          }
          VariableType type = VariableType.getEnumerationForString(st.sval);
          return type;
      }
      public Statement parseBlock(StreamTokenizer st)
              throws PascalParseError,IOException
      {
          BlockStatement block = new BlockStatement(); assert block != null;
          int token = st.nextToken();
          System.out.println("parseBlock/READING " + st.sval);
          if(token == '[') token = parseCompilerHint(st, block);
          // Check existance of a constant or variable
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("expected a BEGIN or LABEL or CONST or VAR here ");
          }
          if ("LABEL".equalsIgnoreCase(st.sval) == true)
          {
              parseLabels(st, block);
              token = st.nextToken();
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
          
          if(token == '[') token = parseCompilerHint(st, null);
          
          if(token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("Programs begin with PROGRAM");
          }
          
          if("PROGRAM".equalsIgnoreCase(st.sval)== false)
          {
              throw new PascalParseError("Programs must begin with PROGRAM got "+ st.sval);
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
      
      public int parseCompilerHint (StreamTokenizer st, BlockStatement block)
              throws PascalParseError, IOException
      {
          st.pushBack();
          int token = st.nextToken();
          if(token == '[') // check for start
          {
              System.out.println(" found start of a [ ");
              String result = "[";
              do
              {
                  if(token == StreamTokenizer.TT_WORD)
                  {
                      // To do could check
                      result = result + " " + st.sval;
                      System.out.println(" & " + st.sval);
                  } // To do handle others
                  token = st.nextToken();
                  // WARNING COULD RUN OUT IF BADLY CODED
              }while(token != ']');
              
              System.out.println("IGNOREING "+ result);
              return st.nextToken();
          }else
          {
              return token;
          }
          //assert false:"Unreachable";
      }
      
      public TreePart booleanExpression(StreamTokenizer st)
              throws PascalParseError,IOException
      {
          TreePart first = expression(st);
          int token = st.nextToken();
          if ( (token == '<')|| (token == '>') || (token == '='))
          { // SURE ITS A COMPARISON
              System.out.println("It's a comparison "+ token);
              int secondToken = st.nextToken();
              if((secondToken == '>') || (secondToken == '='))
              {
                  int thing = -1;
                  if( (token == '<') && (secondToken == '>'))
                  {
                      thing = BooleanExpTreePart.NOT_EQUAL;
                  }
                  if( (token == '>') && (secondToken == '='))
                  {
                      thing = BooleanExpTreePart.GREATER_OR_EQUAL;
                  }
                  if( (token == '<') && (secondToken == '='))
                  {
                      thing = BooleanExpTreePart.LESSTHAN_OR_EQUAL;
                  }
                  if(thing == -1)
                  {
                      throw new PascalParseError("started a comparison with "+
                              token + " Didn't figure it out");
                  }
                  TreePart secondPart = expression(st);
                  BooleanExpTreePart bx = new BooleanExpTreePart(first,thing,secondPart);
                  return bx;
                      }
              else
              {
                  st.pushBack();// save the next token for later.
                  int thing = -1;
                  if(token == '<')
                  {
                      thing = BooleanExpTreePart.LESS_THAN;
                  }
                  if(token == '>')
                  {
                      thing = BooleanExpTreePart.GREATER_THAN;
                  }
                  if(token == '=')
                  {
                      thing = BooleanExpTreePart.EQUAL_TO;
                  }
                  TreePart secondPart = expression(st);
                  BooleanExpTreePart bx = new BooleanExpTreePart(first, thing, secondPart);
                  return bx;
              }
              
          }else
          {
              st.pushBack();
          }
          return first;
      }
      
      public TreePart booleanGroupExpression ( StreamTokenizer st)
              throws PascalParseError,IOException
      {
          // 1. TEST IF NOT (EXPRESSION)
          int token = st.nextToken();
          if(token == StreamTokenizer.TT_WORD && "NOT".equalsIgnoreCase(st.sval))
          {
              TreePart secondPart = booleanExpression(st);
              BooleanExpTreePart bx = new BooleanExpTreePart(secondPart,BooleanExpTreePart.NOT, null);
              return bx;
          }else
          {
              st.pushBack(); // Pretend nothing ever happened
          }
          TreePart first = booleanExpression(st);
          token = st.nextToken();
          //2. TEST FOR EXPRESSION AND EXPRESSION
          if(token == StreamTokenizer.TT_WORD)
          {
              if( "AND".equalsIgnoreCase(st.sval))
              {
                  TreePart secondPart = booleanExpression(st);
                  BooleanExpTreePart bx = new BooleanExpTreePart(first, BooleanExpTreePart.AND,
                  secondPart);
                  return bx;
              }
              //3. TEST FOR EXPRESSION OR EXPRESSION
              if("OR".equalsIgnoreCase(st.sval))
              {
                  TreePart secondPart = booleanExpression(st);
                  BooleanExpTreePart bx = new BooleanExpTreePart(first, BooleanExpTreePart.OR,
                  secondPart);
                  return bx;
              }
              st.pushBack(); // wasn't AND or OR
          }else st.pushBack(); //wasn't something I like to think about
          return first;
      }
      
      public Statement ifStatement(StreamTokenizer st)
              throws PascalParseError,IOException
      {
          int token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ||
                  "IF".equalsIgnoreCase(st.sval)==false)
          {
              throw new PascalParseError("Expected IF");
          }
          TreePart expression = booleanGroupExpression(st);
          System.out.println("READ-IN-IF-STATEMENT-THEN");
          token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ||
                  "THEN".equalsIgnoreCase(st.sval)== false)
          {
              throw new PascalParseError("Expected THEN here");
          }
          System.out.println("READ-IN-IF-STATEMENT '"+ st.sval);
          Statement block = allStatements(st);
          //System.out.println("READ-GOT-THIS-FAR");
          token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ||
                  "ELSE".equalsIgnoreCase(st.sval)== false)
          {
              st.pushBack();
              IfTreeStatement ifState = new IfTreeStatement(expression, block);
              return ifState;
          }
          System.out.println ("READ-IN-IF-STATEMENT-ELSE '" + st.sval);
          Statement eblock = allStatements(st);
          IfTreeStatement ifState = new IfTreeStatement(expression, block, eblock);
          // NOTICE NO ending ';'
          return ifState;
          
          
      }
      
      public Statement whileStatement(StreamTokenizer st)
              throws PascalParseError,IOException
      {
          int token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ||
                  "WHILE".equalsIgnoreCase(st.sval)==false)
          {
              throw new PascalParseError("Expected WHILE here");
          }
          TreePart expression = booleanGroupExpression(st);
          System.out.println("READ-IN-WHILE-STATEMENT-DO");
          token = st.nextToken();
          if(token != StreamTokenizer.TT_WORD ||
                  "DO".equalsIgnoreCase(st.sval) == false)
          {
              throw new PascalParseError("Expected DO here");
          }
          System.out.println("READ-IN-WHILE-STATEMENT '"+ st.sval);
          Statement block = allStatements(st);
          
          WhileTreeStatement whileState = new WhileTreeStatement(expression, block);
          return whileState;
      }
      
      public Statement repeatStatement(StreamTokenizer st)
              throws PascalParseError, IOException
      {
          int token = st.nextToken();
          if (token != StreamTokenizer.TT_WORD ||
                  "REPEAT".equalsIgnoreCase(st.sval) == false)
          {
              throw new PascalParseError("Expected REPEAT");
          }
          BlockStatement block = new BlockStatement(); assert block != null;
          boolean keepGoing = true;
          do
          {
              Statement s = allStatements(st);
              block.add(s);
              token = st.nextToken();
              if(token != StreamTokenizer.TT_WORD)
              {
                  throw new PascalParseError("expected and UNTIL here at this block" + token);
              }
              if("UNTIL".equalsIgnoreCase (st.sval) == true)
              {
                  keepGoing = false;
                  System.out.println("FOUND UNTIL");
              }else
              {
                  st.pushBack();
              }
                  
              }while (keepGoing == true);
              TreePart expression = booleanGroupExpression(st);
              token = st.nextToken();
              if(token != ';')
            {
                throw new PascalParseError(" expected semi colon here ");
            }
            RepeatStatement repeatState = new RepeatStatement(block, expression);
            return repeatState;
          }
      
      public TreePart parseVariable(StreamTokenizer st)
              throws PascalParseError, IOException
      {
          int token = st.nextToken();
          if( token != StreamTokenizer.TT_WORD)
          {
              throw new PascalParseError("EXPECTED variable name in expression ");
          }
          // todo check here if the variable has been defined
          String name = st.sval;
          System.out.println("PARSE VARIABLE called << "+name + ">> ");
          VariablePart var = makeVariable(st.sval);
          
          token = st.nextToken();
          if(token == '[')
          {
              System.out.println("START ARRAY EXPRESSION");
              TreePart expression = booleanGroupExpression(st);
              System.out.println("END ARRAY EXPRESSION");
              token = st.nextToken();
              if(token != ']')
              {
                  throw new PascalParseError("EXPECTED ] Not " + token);
              }
              ArrayPart it = new ArrayPart(var, expression);
              return it;
          }else st.pushBack();
          
          System.out.println("parseVariable END");
          return var;
          
      }
} // END CLASS 
 

