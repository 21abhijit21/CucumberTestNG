package com.prudential.com.utilities;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;


public class DBUtilities {
	
	public ArrayList<Integer> Fields = new ArrayList();
	public ArrayList<Object> Result = new ArrayList();
	
	public ArrayList<Object> Get_Coupon_From_Informix(String Coupon) throws InstantiationException,
    IllegalAccessException {
		
		Fields.clear();
		Result.clear();
		
	Fields.add(2);
	Fields.add(3);
	Fields.add(6);
	Fields.add(9);
	Fields.add(10);
	
	int Loop = Fields.size();
	System.out.println(Fields);
	System.out.println(Loop);

try {
    Class.forName("com.informix.jdbc.IfxDriver").newInstance();
    System.out.println("driver loaded...");
} catch (ClassNotFoundException e) {
    System.out.println("Error in loading the driver..." + e);
    System.exit(0);
}
try {
    Connection dbConnection = DriverManager
            .getConnection("jdbc:informix-sqli://tflhpgld.finishline.com:1698/standard:informixServer=itest_gold1_net","arb","Ab12345!");
    System.out.println("Connection successful...");
    Statement statement = dbConnection.createStatement();


    
    for (int i=0;i<Loop;i++){
        ResultSet resultSet= statement.executeQuery("select * from coupon_xref x\r\n" + 
        		"inner join coupon_header h\r\n" + 
        		"on h.id=x.cpn_hdr_id\r\n" + 
        		"where cpn_no='"+Coupon+"'");
    	int DB = Fields.get(i);
    	System.out.println(DB);
    		 if (resultSet.next()){
    	Object str = resultSet.getObject(DB);	
    	String output = ((String) str).replaceAll("\\s","");
    			 
     //   String str = resultSet.getString(DB);
        Result.add(output);
    		 }
        
    }

} catch (SQLException e) {
    System.out.println("database-ConnectionError: " + e);
    System.exit(0);
}
System.out.println(Result);
return Result;
}



public ArrayList<Object> Get_Loyalty_Coupon_From_XBR(String Coupon, String Loyalty) throws InstantiationException,
IllegalAccessException {
	Fields.clear();
	Result.clear();
Fields.add(1);
Fields.add(2);
Fields.add(3);
Fields.add(4);
Fields.add(5);
Fields.add(6);
Fields.add(7);
Fields.add(8);

int Loop = Fields.size();
System.out.println(Fields);
System.out.println(Loop);

try {
//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
//   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
System.out.println("driver loaded...");
} catch (ClassNotFoundException e) {
System.out.println("Error in loading the driver..." + e);
System.exit(0);
}
try {
Connection dbConnection = DriverManager
        .getConnection("jdbc:jtds:sqlserver://titanium.finishline.com:1433;databaseName=XBR;domain=finishline.com;useNTLMv2=true;","C001907","S17154151m!");
System.out.println("Connection successful...");
Statement statement = dbConnection.createStatement();



for (int i=0;i<Loop;i++){
    ResultSet resultSet= statement.executeQuery("select * from xbr_coupon_cust_tab cust\r\n" + 
    		"inner join  mst_coupon_t cpn\r\n" + 
    		"on cpn.coupon_num=cust.coupon_num\r\n" + 
    		"where customer_id='"+Loyalty+"' and cpn.coupon_num = '"+Coupon+ "'");
	int DB = Fields.get(i);
	System.out.println(DB);
		 if (resultSet.next()){
	Object str = resultSet.getObject(DB);	
//	String output = ((String) str).replaceAll("\\s","");
			 
 //   String str = resultSet.getString(DB);
    Result.add(str);
		 }
    
}

} catch (SQLException e) {
System.out.println("database-ConnectionError: " + e);
System.exit(0);
}
System.out.println(Result);
return Result;
}


public Object Loyalty_Points_on_xbr_crm_points(String Loyalty) throws InstantiationException,
IllegalAccessException {

	Result.clear();
	
try {
//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
//   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
System.out.println("driver loaded...");
} catch (ClassNotFoundException e) {
System.out.println("Error in loading the driver..." + e);
System.exit(0);
}
try {
Connection dbConnection = DriverManager
        .getConnection("jdbc:jtds:sqlserver://titanium.finishline.com:1433;databaseName=XBR;domain=finishline.com;useNTLMv2=true;","C001907","S17154151m!");
System.out.println("Connection successful...");
Statement statement = dbConnection.createStatement();


    ResultSet resultSet= statement.executeQuery("select * from xbr_crm_points\r\n" + 
    		"where customer_id='"+Loyalty+"' order by updated_on desc");
		 if (resultSet.next()){
	Object str = resultSet.getObject(14);	
//	String output = ((String) str).replaceAll("\\s","");
			 
 //   String str = resultSet.getString(DB);
    Result.add(str);
		 }

} catch (SQLException e) {
System.out.println("database-ConnectionError: " + e);
System.exit(0);
}

System.out.println(Result);
Object Points = Result.get(0);
return Points;
}



}
