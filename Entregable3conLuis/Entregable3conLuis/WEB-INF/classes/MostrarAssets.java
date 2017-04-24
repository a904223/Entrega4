import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// leer base de datos y mostrar datos
@SuppressWarnings("serial")
public class MostrarAssets extends HttpServlet {
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
        resp.setContentType("text/html");
        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }

        String sql = "Select AssetName, PurchaseYear, InitialPrize from Amortization";
        System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString=" ";
            boolean first = true;
            while(result.next()) {
                if (!first) {
                   resString += ",";
                } else {
                    first = false;
                }
                resString += "\" " + result.getString("AssetName")+ " ,";
				resString += "   " + result.getString("PurchaseYear")+ " ,";
                resString += "   " + result.getString("InitialPrize") + " \" ";
            } 
            
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>All the assets of the company</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<center>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<B><FONT size=+2>All the assets of the company </FONT></B>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<p><FONT size=+1>ASSETS: " + resString + "</FONT></p>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<BR><a href=\"Amortization.html\">go to Amortization</a>");
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

