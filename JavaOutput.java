// GENERATED CODE 
package chapter2 ; 
import java.sql.*;
// PROGRAM $$PROGRAMNAME$$
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
 
// TYPES//-------------------------------
 static class  COLOUR 
{
  int red;
 int green;
 int blue;
}; //END OF CLASS 
public static void main(String[] args) 
{
 gConnectTodatabase();
// VARS 
double mynumber  = 0 ;

COLOUR mycolor  = new COLOUR(); //simulate stack allocation ;

// END VARS 
// BEGIN CODE
 
{
 // BLOCK BEGINING // BEGIN CODE
mycolor.red = (int) 34.0; //With ASSIGNMENT

} // END BLOCK 

System.out.println(mycolor.red);/* WriteLn */ 
mynumber = (45.0 * 34.0);//ASSIGNMENT 

System.out.println(mynumber);/* WriteLn */ 
} // END BLOCK 
 
 } // END PROGRAM
