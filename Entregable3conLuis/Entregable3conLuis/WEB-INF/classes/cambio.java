import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class cambio extends HttpServlet {
	int i=0;
	Connection connection;
	
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection=DriverManager.getConnection("jdbc:odbc:Database"); 
        } catch(Exception e) {
            e.printStackTrace();
        }
  } 
	
	

    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String correo = req.getParameter("correo");
		resp.setContentType("text/html");
        PrintWriter toClient = resp.getWriter();
		try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("Select Password FROM Users WHERE mail like '" + correo + "'");
   
   while (result.next()) {
        
		i=i+1;
		String Pass=result.getString("Password");
		toClient.println("<html>");
		toClient.println("<head>");
		toClient.println("<title>Password change</title>");
		toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
		toClient.println("</head>");
		toClient.println("<body>");
		toClient.println("<div class=\"header\">");
		toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
		toClient.println("<h1 align=\"center\">Change Succesful!</h1>");
		toClient.println("</div>");
		toClient.println("<center>");
		toClient.println("<P>");
		toClient.println("Your new Password is <B>" + Pass + "</B>. Change it as soon as possible.");
		toClient.println("<P>");
		toClient.println("<P>");
            
        }
		}
		catch(SQLException e) {
            e.printStackTrace();
            System.out.println("nada");
        }

		
			
		
		toClient.println("<a href=\"Login.html\">Back to Login</a>");
		toClient.println("</body>");
		toClient.println("</head>");
		
        
 
    } 

  
}
