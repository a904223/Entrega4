import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;


@SuppressWarnings("serial")
public class NewProduct extends HttpServlet {
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
    
	
		String Sell = req.getParameter("Sell");
		String Name = req.getParameter("Name");
		String Buy = req.getParameter("Buy");
					 
		String sql = "INSERT INTO Products ( Product_Name, Sell_Price, Buy_Price) VALUES ('";
		sql +=    Name+"'" ;
		sql +=  ", " + Sell  ;
		sql +=  ", " + Buy + ")";
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
    toClient.println("<TITLE>Product</TITLE>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
    toClient.println("</HEAD>");
    toClient.println("<BODY");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">New Product:</h1>");
    toClient.println("<P><FONT face=Agency FB size=+1><B>Product Name: </B>" + Name + "</FONT>");
    toClient.println("<BR><FONT face=Agency FB size=+1><B>Sell Price: </B>" + Sell + "</FONT>");
    toClient.println("<P><FONT face=Agency FB size=+1> <B>Buy Price: </B>" + Buy + "</FONT>");
    toClient.println("</BODY>");
	toClient.println("<p>&nbsp;</p>");
    toClient.println("<BR><a style=\"font-size:24px\" href=\"products\">Back to Products</a>");

    toClient.println("</HTML>");


    toClient.flush();
    toClient.close();
  }
     

  public String getServletInfo() {
    return "New Product";
  } 
}