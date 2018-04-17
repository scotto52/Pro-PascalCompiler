// GENERATED CODE 
package propascal.transcompiler; 
import java.sql.*;
// PROGRAM TESTPROCEDUREDO
class JavaOutput 
{
   static Connection gTheDatabaseConnection = null; 
   static void gConnectTodatabase()
   { 
       try {
           Class.forName("org.sqlite.JDBC");
           gTheDatabaseConnection = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
       }
       catch (Exception e) 
       {
           System.err.println(e.getClass().getName() + ": " + e.getMessage() ); 
           System.exit(0);
       }
       System.out.println("database successfully created");
   }

static void gDisconnectToDatabase()
{ 
 try 
  { 
   if(gTheDatabaseConnection!=null) gTheDatabaseConnection.close();
   gTheDatabaseConnection = null; 
  } catch (Exception e) 
  {
   System.err.println("DISCONNECT" + e.getClass().getName() + ": " + e.getMessage() );
   System.exit(0);
  }
  
  System.out.println("database successfully DISCONNECTED"); 
} 

/*  ******PROCEDURES ****** */

//------------------------------------------------------
 public static void isThisworking() 
{
 // BLOCK BEGINING // VARS 
int localvar  = 0 ;

// END VARS 
// BEGIN CODE
localvar = 1.0;//ASSIGNMENT 

System.out.println(localvar);/* WriteLn */ 
} // END BLOCK 
// END PROCEDURE isThisworking

/*   ****** END  PROCEDURES  ******  */
public static void main(String[] args) 
{
 gConnectTodatabase();
// BEGIN CODE
System.out.println(34.0);/* WriteLn */ 

isThisworking(); // pascal procedure 

gDisconnectToDatabase();
} // END BLOCK 
 
 } // END PROGRAM
