import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;


@SuppressWarnings("serial")
public class NewSupplier extends HttpServlet {
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
    
	
		String CIF = req.getParameter("CIF");
		String Name = req.getParameter("Name");
		String TLF = req.getParameter("TLF");
		String Email = req.getParameter("Email");
					 
		String sql = "INSERT INTO Suppliers ( SupplierName, CIF, Telephone, Email) VALUES ('";
		sql +=    Name+"'" ;
		sql +=  ", '" + CIF + "'";
		sql +=  ", '" + TLF + "'";
		sql +=  ", '" + Email + "')";
	   System.out.println("Insert sql: " + sql);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();

         
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error addding new Supplier: " + e);
        }

	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    toClient.println("<!DOCTYPE HTML>");
        

    toClient.println("<HTML>");
    toClient.println("<HEAD>");
    toClient.println("<TITLE>Supplier</TITLE>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
    toClient.println("</HEAD>");
    toClient.println("<BODY");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">New Supplier:</h1>");
    toClient.println("<P><FONT face=Agency FB size=+1><B>Supplier Name: </B>" + Name + "</FONT>");
    toClient.println("<BR><FONT face=Agency FB size=+1><B>CIF: </B>" + CIF + "</FONT>");
    toClient.println("<P><FONT face=Agency FB size=+1> <B>Telephone: </B>" + TLF + "</FONT>");
	toClient.println("<P><FONT face=Agency FB size=+1> <B>Email: </B>" + Email + "</FONT>");
    toClient.println("</BODY>");
	toClient.println("<p>&nbsp;</p>");
    toClient.println("<BR><a style=\"font-size:24px\" href=\"suppliers\">Back to Suppliers</a>");

    toClient.println("</HTML>");


    toClient.flush();
    toClient.close();
  }
     

  public String getServletInfo() {
    return "New Supplier";
  } 
}