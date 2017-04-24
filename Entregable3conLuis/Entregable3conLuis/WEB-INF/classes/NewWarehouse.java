import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;


@SuppressWarnings("serial")
public class NewWarehouse extends HttpServlet {
	Connection connection;
     
  
     public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
        
           

  public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    
	
		String Id = req.getParameter("Id");
		String Name = req.getParameter("Name");
		String Adress = req.getParameter("Adress");
					 
		String sql = "INSERT INTO Warehouse ( Warehouse_Id, Warehouse_Name, Adress) VALUES (";
		sql +=    Id ;
		sql +=  ", '" + Name + "'";
		sql +=  ", '" + Adress + "')";
	   System.out.println("Insert sql: " + sql);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();

         
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error addding new Warehouse: " + e);
        }

	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    toClient.println("<!DOCTYPE HTML>");
        

    toClient.println("<HTML>");
    toClient.println("<HEAD>");
    toClient.println("<TITLE>WAREHOUSE</TITLE>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
    toClient.println("</HEAD>");
    toClient.println("<BODY");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">New Warehouse:</h1>");
    toClient.println("<P><FONT face=Agency FB size=+1><B>Warehouse Id: </B>" + Id + "</FONT>");
    toClient.println("<BR><FONT face=Agency FB size=+1><B>Warehouse Name: </B>" + Name + "</FONT>");
    toClient.println("<P><FONT face=Agency FB size=+1> <B>Adress: </B>" + Adress + "</FONT>");
    toClient.println("</BODY>");
	toClient.println("<p>&nbsp;</p>");
    toClient.println("<BR><a style=\"font-size:24px\" href=\"warehouses\">Back to Warehouses</a>");

    toClient.println("</HTML>");


    toClient.flush();
    toClient.close();
  }
     

  public String getServletInfo() {
    return "New Warehouse";
  } 
}