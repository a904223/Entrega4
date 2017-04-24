import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class signup extends HttpServlet {
    Connection connection;
     
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando IntroducirPedido...");
  }

  public void destroy() {
    System.out.println("There is nothing to do...");
  }

  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    HttpSession session = req.getSession(true);
    String sesion = (String)session.getAttribute("sesion");
    String strName = req.getParameter("name");
    String strPassword = req.getParameter("password");
    String strPregunta = req.getParameter("question");
	String strAnswer = req.getParameter("answer");
    
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println("ok");
            PreparedStatement ps = connection.prepareStatement("insert into Users (Mail,Password,Security_Question,Answer)values(?,?,?,?)");
                 
                ps.setString(1,strName);
                ps.setString(2, strPassword);
                ps.setString(3, strPregunta);
				ps.setString(4, strAnswer);
                ps.executeUpdate();
                connection.close(); 
            //fileWriter.close();
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println("no ok:");
            //fileWriter.close();
            e.printStackTrace();
        }
                 
    //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
    //PrintWriter toFile = new PrintWriter(fileWriter);
    
    //toFile.println("> " + strCustomer + "\t" + strProduct + "\t" + strPrice + "\t" + strDate + "\t" + sesion);
    //fileWriter.close();
    devolverPaginaHTML(resp, strName,  strPassword, strPregunta, strAnswer);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp, String name, String password, String pregunta, String answer ) {

    resp.setContentType("text/html");

    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("There has been an exception");    
    }
        
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Sign Up</TITLE>");
    out.println("<link rel=\"Stylesheet\" type=\"text/css\" href=\"pattern.css\">");
    out.println("</HEAD>");
    out.println("<BODY background=\"gris.jpg\">");
    out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">Sign Up</h1></div>");
    out.println("<center>");
    out.println("<B><font  size=\"7\" color=\"#088A85\" face=\"Calibri\">You have been registered</FONT></B>");
    out.println("<P><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Mail: </B><font color=\"#000000\">" + name + "</font></FONT>");
    out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Password: </B><font color=\"#000000\">" + password + "</font></FONT>");
    out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Question: </B><font color=\"#000000\">" + pregunta + "</font></FONT>");
	out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Answer: </B><font color=\"#000000\">" + answer + "</font></FONT>");
    out.println("</BODY>");
    out.println("<BR><a href=\"Login.html\">Back to the login</a>");
    out.println("</center>");
    
   

    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}