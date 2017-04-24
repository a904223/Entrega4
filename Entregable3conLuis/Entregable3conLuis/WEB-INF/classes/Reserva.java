import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;


@SuppressWarnings("serial")
public class Reserva extends HttpServlet {
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
    
	
		String Customer = req.getParameter("customer");
		String Product = req.getParameter("product");
		String date = req.getParameter("date");
					 
		String sql = "INSERT INTO bills ( Customer, Product, Bill_date) VALUES (";
		sql +=  "'" + Customer + "'";
		sql +=  ", " + Product;
		sql +=  ", #" + date + "#)";
	   System.out.println("Insert sql: " + sql);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();

         
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error addding new Bill: " + e);
        }

	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    toClient.println("<!DOCTYPE HTML>");
        

    toClient.println("<HTML>");
    toClient.println("<HEAD>");
    toClient.println("<TITLE>Bills</TITLE>");
    toClient.println("</HEAD>");
    toClient.println("<BODY");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">Bill:</h1>");
    toClient.println("<P><FONT face=Agency FB size=+1><B>Customer: </B>" + Customer + "</FONT>");
    toClient.println("<BR><FONT face=Agency FB size=+1><B>Price: </B>" + Product + "</FONT>");
    toClient.println("<P><FONT face=Agency FB size=+1> <B>Bill date: </B>" + date + "</FONT>");
    toClient.println("</BODY>");
	toClient.println("<p>&nbsp;</p>");
    toClient.println("<BR><a style=\"font-size:24px\" href=\"bills.html\">Back to Bills menu</a>");

    toClient.println("</HTML>");


    toClient.flush();
    toClient.close();
  }
     

  public String getServletInfo() {
    return "New Bills";
  } 
}