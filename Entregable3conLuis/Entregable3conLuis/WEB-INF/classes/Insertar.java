import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// insertar en base de datos
@SuppressWarnings("serial")
public class Insertar extends HttpServlet {
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
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Asset = req.getParameter("asset");
        String Year = req.getParameter("year");
		String Life = req.getParameter("life");
        String Prize = req.getParameter("prize");
	
        String sql = "INSERT INTO Amortization (AssetName, PurchaseYear, UsefulLife, InitialPrize) VALUES (";
        sql +=  "'" + Asset + "'";
		sql +=  ", '" + Year+ "'";
		sql +=  ", '" + Life+ "'";
		sql +=  ", '" + Prize+ "')";
        System.out.println("Insert sql: " + sql);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
         
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error en insertar un usuario nuevo: " + e);
        }
        
		PrintWriter out = null;
		try {
		out=resp.getWriter();
		} catch (IOException io) {
		System.out.println("Se ha producido una excepcion");    
		}
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>New assets</TITLE>");
		out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
		out.println("</HEAD>");
		out.println("<BODY>");
		
		out.println("<div class=header>");
        out.println("<img align=left src=\"./Logo ERP Tecnun.png\">");
		out.println("<h1 align=center>Accounting - Amortization</h1>");
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
      
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
        out.println(" </div>");
        out.println("</li>");
		out.println("</ul>");

		out.println("<body bgcolor=#FFFFFF text=#631818>");
		out.println("<center>");
		out.println("<BR>");
		out.println("</BR>");

		out.println("<B><FONT size=+2>New asset of the company </FONT></B>");
		out.println("<P><FONT size=+1><B>Asset: </B>" + Asset + "</FONT>");
		out.println("<BR><FONT size=+1><B>Year: </B>" + Year + "</FONT>");
		out.println("<BR><FONT size=+1><B>Life: </B>" + Life + "</FONT>");
		out.println("<BR><FONT size=+1><B>Prize: </B>" + Prize + "</FONT>");

		out.println("<BR>");
		out.println("<BR><a href=\"Amortization.html\">Go to amortization</a>");
		out.println("</center>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
	} 

}