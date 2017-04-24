import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EnvioCorreo extends HttpServlet {
	int i=0;
	Connection connection;
     

  public void destroy() {
    System.out.println("Nothing to do...");
  }
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection=DriverManager.getConnection("jdbc:odbc:Database"); 
        } catch(Exception e) {
            e.printStackTrace();
        }
  } 
          

  public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	res.setContentType("text/html");
	String correo = req.getParameter("correo");


    PrintWriter out = null;
    try {
      out=res.getWriter();
    } catch (IOException io) {
      System.out.println("Exception"); 
	
    }
	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    toClient.println("<!DOCTYPE HTML>");
	toClient.println("<HTML>");
    toClient.println("<HEAD>");
	toClient.println("<title>Recover Password</title>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
	toClient.println("</head>");
    toClient.println("<BODY >");
	toClient.println("<div class=\"header\">");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">Recover Password</h1>");
	toClient.println("</div>");
	toClient.println("<meta align=\"right\" http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
	toClient.println("<TR>");
	
   try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("Select Security_Question FROM Users WHERE mail like '" + correo + "'");
   
   while (result.next()) {
        
		i=i+1;
		String Question=result.getString("Security_Question");
		toClient.println( "<center>");
		toClient.println( "<div>");
		toClient.println( "<B> Security Question:  </B>");
		toClient.println( "<P>");
		toClient.println(Question);
		toClient.println("</div>");
		toClient.println("</center>");
		toClient.println("<center>");
		toClient.println("<TD>");
		toClient.println("</TD>");
		toClient.println("</center>");
            
        }
		}
		catch(SQLException e) {
            e.printStackTrace();
            System.out.println("nada");
        }
		

	toClient.println("<form method=\"get\" action=\"Comprobacion\">");
	toClient.println("<center>");
	toClient.println("<table>");
	toClient.println("<tr>");
	toClient.println("<td align=\"center\"><input type=\"text\" name=\"question\" size=\"25\" />");
	toClient.println("<td align=\"center\"><input type=\"hidden\" name=\"correo\"  value=\"" + correo + "\" />");
	toClient.println("</tr>");
	toClient.println("<tr>");
	toClient.println("<td align=\"right\">");
	toClient.println("<input type=\"submit\" value=\"Send\">");
	toClient.println("</td>");
	toClient.println("<td align=\"left\">");
	toClient.println("<input type=\"reset\" value=\"Reset\">");
	toClient.println("</td>");
	toClient.println("</tr>");
	toClient.println("</table>");
	toClient.println("</center>");
	toClient.println("</div>");
	toClient.println("</li>");
	toClient.println("</ul>");
	toClient.println("</BODY>");
    toClient.println("</HTML>");
	toClient.flush();
    toClient.close();
	}
	}
