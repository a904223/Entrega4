import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// leer base de datos y mostrar datos
@SuppressWarnings("serial")
public class MostrarIncomeStatement extends HttpServlet {
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
	
        
		String sql = "Select sum(sold) - sum(expences) AS total FROM IncomeStatement where date between # 1/01/2017 # and # 31/12/2017 #";
        System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString=" ";
			String resString2=" ";
            boolean first = true;
            while(result.next()) {
                if (!first) {
                   resString += ",";
				   resString2 += ",";
                } else {
                    first = false;
                }
                resString += "\" " + result.getString("total")+ " \" ";
				
				
            } 
            
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Net Income of the company</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<center>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<B><FONT size=+2>Net Income of the company in the last year </FONT></B>");
		out.println("<BR>");
		out.println("</BR>");
		out.println("<p><FONT size=+1>Total: " + resString + "</FONT></p>");
		out.println("<BR>");
	
		out.println("</BR>");
	
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

