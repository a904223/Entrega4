import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CheckPassword extends HttpServlet {
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

        String nombre = req.getParameter("usuario");
        String Contrasena = req.getParameter("passw");
	 

		
		resp.setContentType("text/html");
        PrintWriter toClient = resp.getWriter();
		PrintWriter out=null;		
		try {
            out=resp.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
		
		
        if(nombre==null) {
            System.out.println("Problem reading username from request");
            return;
        }
        if(Contrasena==null) {
          System.out.println("Problem reading password from request");
          return;
        }
        Statement stmt = null;
        try {
            stmt=connection.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT Mail, Password FROM Users WHERE Mail = '"
               + nombre + "' AND Password = '" + Contrasena + "'");

            if(rs.next() == false){
               
				out.println("<HTML>");
				out.println("<HEAD>");
				out.println("</HEAD>");
				out.println("<TITLE>Error in login</TITLE>");
				out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
				out.println("</HEAD>");
				out.println("<div class=header>");
				out.println("<img align=left src=\"./Logo ERP Tecnun.png\">");
				out.println("<h1 align=center>TECNUN ERP</h1>");
				out.println("</div>");
				out.println("<BODY>");
				out.println("<center>");
				out.println("<B><FONT size=+2>ERROR: Incorrect username or password </FONT></B>");
				out.println("<BR>");
				out.println("</BR>");
				out.println("<a href=Login.html>Try to login again</a>");
				out.println("</center>");
				out.println("</BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
		
                return;
            } else {
                HttpSession session = req.getSession(true);
                String username = rs.getString("Mail");
                session.setAttribute("Mail", nombre);
				resp.sendRedirect("menu.html");
                return;
            }
        } catch (SQLException sql) {
            System.out.println("Error creating Statement");
            System.out.println(sql.getMessage());
            return;
        } finally {      
            if(stmt!=null) {
                try {
                    stmt.close();
                } catch(SQLException e) {
                    System.out.println("Error closing Statement");
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } 
    } 
}
	  
     
 