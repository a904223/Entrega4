import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class TotalIncome extends HttpServlet {
	Connection connection;
	

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            e.printStackTrace();
        }
		System.out.println("Starting ComprobarContrasena...");
	}
	
	public void destroy() {
		super.destroy();
        System.out.print("Closing connection ...");
        try {
            connection.close();   
			System.out.println("Connection closed");
        } catch(SQLException ex){
            System.out.println("Problem closing connection");
            System.out.println(ex.getMessage());
        }
    }

    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dFrom = req.getParameter("dateFrom");
        String dUntil = req.getParameter("dateUntil");
	 
		resp.setContentType("text/html");
        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
		
		String sql = "Select sum(sold) - sum(expences) AS total FROM IncomeStatement where date between #" + dFrom + "# and #" + dUntil + "#";
		
		System.out.println(sql);
		

  
		try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString=" ";
			
            boolean first = true;
            while(result.next()) {
                if (!first) {
                   resString += " ";
				 
                } else {
                    first = false;
                }
                resString += " " + result.getString("total")+ " ";
				
        }
            
			
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>total Income Statement</TITLE>");
		out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
		out.println("</HEAD>");
		out.println("<BODY>");
		
		out.println("<div class=header>");
        out.println("<img align=left src=\"./Logo ERP Tecnun.png\">");
       
		out.println("<h1 align=center>Accounting - Income Statement</h1>");
		out.println("<p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a></p>");
		
    
			out.println("</div>");
		out.println("<ul class=navbar>");
        out.println("<li class=dropdown-1><a class=menu class=dropbtn><font face=Arial>Menu</font></a>");
        out.println(" <ul>");
        out.println("<div class=dropdown-content-1>");
        out.println(" <li class=dropdown-2><a class=dropbtn class=active>Purchases</a>");
		out.println("<ul>");
		
        out.println(" <div class=dropdown-content-2>");
		out.println(" <a href=suppliers>Suppliers</a>");
        out.println("<a href=#>New Order</a>");
		out.println("<a href=#>Orders</a>");
		out.println("</div>");
        out.println("</ul>");
        out.println(" </li>");
        out.println("<li class=dropdown-2><a class=dropbtn>Sales</a>");
        out.println(" <ul>");
		out.println("<div class=dropdown-content-2>");
        out.println("<a href=customers>Customers</a>");
		  out.println("<a href=#>New Order</a>");
		out.println("<a href=#>Orders</a>");
		out.println("</div>");
        out.println("</ul>");
        out.println(" </li>");
		out.println(" <li class=dropdown-2><a class=dropbtn>Inventory</a>");
        out.println("<ul>");
		out.println("<div class=dropdown-content-2>");
        out.println(" <a href=products>Products</a>");
		  out.println(" <a href=warehouses>Warehouse</a>");
		  out.println("</div>");
        out.println("</ul>");
        out.println(" </li>");
		out.println("<li class=dropdown-2><a class=dropbtn href=accounting.html>Accounting</a></li>");
		out.println("<li class=dropdown-2><a class=dropbtn href=bills.html>Bills</a></li>");
		out.println("</div>");
        out.println("</ul>");
        out.println(" </li>");
        out.println("</ul>");
      
		
	


		out.println("<body bgcolor=#FFFFFF text=#631818>");
		out.println("<center>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<B><FONT size=+2>Net Income of the company </FONT></B>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<BR>");
		out.println("<B>Between " + dFrom + " and " + dUntil + "</B>");
		out.println("</BR>");
		out.println("<p><FONT size=+1>costs: " + resString + " </FONT></p>");
		out.println("</BR>");
	
	
		out.println("<BR><a href=\"IncomeStatement.html\">select different dates</a>");
		out.println("</center>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		
	   
        } catch(SQLException e) {
			
            e.printStackTrace();
		
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        out.close();	
	}
}