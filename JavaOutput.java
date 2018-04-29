// GENERATED CODE 
package propascal.transcompiler; 
import java.sql.*;
// PROGRAM ROD
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
// LABELS 
(LABEL) errlab;
// TYPES//-------------------------------
 static class  IoStatus 
{
  int DeviceInfo;
 double IoStat;
}; //END OF CLASS 
//-------------------------------
 static class  ItemList 
{
  double BufLen;
 int Terminator;
 int BufAddr;
}; //END OF CLASS 
//-------------------------------
 static class  QUADWORD 
{
  int Hi;
 int Lo;
}; //END OF CLASS 

/*  ******PROCEDURES ****** */

//------------------------------------------------------
 public static void HandleTubsReturned() 
{
 // BLOCK BEGINING // VARS 
int debug = 0.0;

int sqlca.sqlcode = 0.0;

int sqllocation = 0.0;

int elementsmonitored = 0.0;

int tubtinno = 0.0;

int nodata = 0.0;

// END VARS 
// BEGIN CODE
if(debug) 
 { 
 System.out.println(78.0);/* WriteLn */  
} 
do {  
{
 // BLOCK BEGINING // BEGIN CODE
sqllocation = 180.0;//ASSIGNMENT 

if((sqlca.sqlcode == nodata)) 
 { 
 sqllocation = 179.0;//ASSIGNMENT 
 
} else 
 { 
  
{
 // BLOCK BEGINING // BEGIN CODE
if((tubtinno[ 1.0 ] == 1.0)) 
 { 
  
{
 // BLOCK BEGINING // BEGIN CODE

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
 
} else 
 { 
 if((tubtinno[ 1.0 ] == 2.0)) 
 { 
  
{
 // BLOCK BEGINING // BEGIN CODE

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
 
}  
} 
sqllocation = 250.0;//ASSIGNMENT 

if((elementsmonitored > 0.0)) 
 { 
  
{
 // BLOCK BEGINING // BEGIN CODE
sqllocation = 260.0;//ASSIGNMENT 


 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update BRYAN SET RCKRET = 1.0 WHERE TubID = " + tubtinno + "" ; 
  Statement stmt = gTheDatabaseConnection.createStatement();
  stmt.executeUpdate(sqlInject);
  stmt.close();
 } catch(SQLException e)
 {
  System.out.println(e.getMessage());
 }

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
 
} else 
 { 
  
{
 // BLOCK BEGINING // BEGIN CODE
sqllocation = 280.0;//ASSIGNMENT 


 //SQL UPDATE 
 assert gTheDatabaseConnection != null ; 
 try 
 { 
  String sqlInject = "Update BRYAN SET CS2ENT = NULL  WHERE TubID = " + tubtinno + "" ; 
  Statement stmt = gTheDatabaseConnection.createStatement();
  stmt.executeUpdate(sqlInject);
  stmt.close();
 } catch(SQLException e)
 {
  System.out.println(e.getMessage());
 }

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
 
} 

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
 
} 

//SQLCommitWorkStatementassert gTheDatabaseConnection != null ; 
try 
 {
   gTheDatabaseConnection.commit();
 } catch(SQLException e)
 {
   System.out.println(e.getMessage());
 }

} // END BLOCK 
} 
 while ((sqlca.sqlcode == nodata)) 

} // END BLOCK 
// END PROCEDURE HandleTubsReturned

/*   ****** END  PROCEDURES  ******  */
public static void main(String[] args) 
{
 gConnectTodatabase();
// VARS 
char rslinkstatus  = 0 ;

double banchan  = 0 ;

char bobnow  = 0 ;

double delaytime  = 0 ;

int elementsmonitored  = 0 ;

char cntnrfuzz  = 0 ;

static int linkfailure = 4.0;

char fuzztypee  = 0 ;

char uid  = 0 ;

ItemList trnlist  = new ItemList(); //simulate stack allocation ;

int sqllocation  = 0 ;

char odate  = 0 ;

int sysstatus  = 0 ;

boolean linkfault  = 0 ;

char tubtinnos  = new char[24];

boolean debug  = 0 ;

char logname  = 0 ;

static int linkhealthy = 3.0;

int count  = 0 ;

int messagelength  = 0 ;

int i  = 0 ;

char tubtinno  = 0 ;

char bobnoe  = 0 ;

static int nodata = 1403.0;

char bobcntnr  = 0 ;

int oate = 0.0;

char eqivname  = new char[4];

static int banmsglen = 150.0;

IoStatus statblock  = new IoStatus(); //simulate stack allocation ;

static int failure = 0.0;

static int healthy = 1.0;

int linkstatus  = 0 ;

boolean forever  = 0 ;

char pwd  = 0 ;

char fuzztypew  = 0 ;

// END VARS 
// BEGIN CODE

HandleTubsReturned(); // pascal procedure 

System.out.println(cntnrfuzz);/* WriteLn */ 
System.out.println(fuzztypee);/* WriteLn */ 
System.out.println(fuzztypew);/* WriteLn */ 
System.out.println(bobcntnr);/* WriteLn */ 
System.out.println(bobnoe);/* WriteLn */ 
System.out.println(bobnow);/* WriteLn */ 
System.out.println(tubtinno);/* WriteLn */ 
System.out.println(elementsmonitored);/* WriteLn */ 
System.out.println(rslinkstatus);/* WriteLn */ 
System.out.println(oate);/* WriteLn */ 
gDisconnectToDatabase();
} // END BLOCK 
 
 } // END PROGRAM
