import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// leer base de datos simple y mostrar el total
@SuppressWarnings("serial")
public class total extends HttpServlet {
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

  

        String sql = "SELECT sum( InitialPrize/ (2017-PurchaseYear)) AS Cost FROM Amortization";
        System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString = " ";
            boolean first = true;
            while(result.next()) {
                if (!first) {
                    resString += ",";
                } else {
                    first = false;
                }
               
                resString += "\" " + result.getString("cost") + "\"";
            }
           
			out.println("<HTML>");
			out.println("<HEAD>");
			out.println("</HEAD>");
			out.println("<TITLE>Total amortizate</TITLE>");
			out.println("</HEAD>");
			out.println("<BODY>");
			out.println("<center>");
			out.println("<BR>");
			out.println("</BR>");
			out.println("<B><FONT size=+2>Total amortization of last year </FONT></B>");
			out.println("<BR>");
			out.println("</BR>");
			out.println("<BR><FONT size=+1>Total: " + resString + "</FONT>");
			out.println("<BR>");
			out.println("</BR>");
			out.println("<BR><a href=\"Amortization.html\">go to Amortization</a>");
			out.println("</center>");
			out.println("</BODY>");
			out.println("</HTML>");
	
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        out.close();
    } 
}